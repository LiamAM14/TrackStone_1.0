package es.unex.trackstone10.CU02


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.API.*
import es.unex.trackstone10.ButtonNavigationMenuActivity
import es.unex.trackstone10.CU12.EditDeckActivity
import es.unex.trackstone10.adapter.cardAddDeckAdapter
import es.unex.trackstone10.databinding.ActivitySelectCardDeckBinding
import es.unex.trackstone10.roomdb.TrackstoneDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectCardDeckActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivitySelectCardDeckBinding
    private lateinit var adapter: cardAddDeckAdapter
    private var cardList = (mutableListOf<CardResponse>())
    private var classSelected: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCardDeckBinding.inflate((layoutInflater))
        binding.buttonCreateDeck.text = "Edit Deck"
        var deckId = intent.getIntExtra("DECK_ID", 0)
        var userId = intent.getIntExtra("USER_ID", 0)
        binding.svCard.setOnQueryTextListener(this)
        setContentView(binding.root)
        initRecyclerView(deckId, userId)
        getClassCards()
        binding.buttonCreateDeck.setOnClickListener {
            goToEditCards(deckId)
        }
        binding.buttonFinishDeck.setOnClickListener {
            this.finish()
        }

    }


    private fun initRecyclerView(deckId: Int?, userId: Int?) {
        adapter = cardAddDeckAdapter(cardList, deckId, userId, this)
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCards.adapter = adapter
    }


    private fun getClassCards() {
        CoroutineScope(Dispatchers.IO).launch {
            classSelected = intent.getStringExtra("CLASS_SLUG")
            val classSlug = "$classSelected,neutral"
            val retrofit = APIToken.getRetrofit("/hearthstone/cards/")
            val cards: CardResponseList?


            val call = retrofit.create(APIService::class.java)
                .getCardsByClass(
                    classSlug,
                    "standard",
                    "groupByClass:asc,manaCost:asc",
                    1300,
                    "en_US"
                )
            cards = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    if (cards != null) {
                        val cardsReceived = cards.cards
                        cardList.clear()
                        cardList.addAll(cardsReceived)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    showError()
                }
                hideKeyboard()
            }
        }
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            classSelected = intent.getStringExtra("CLASS_SLUG")
            val classSlug = "$classSelected,neutral"
            val retrofit = APIToken.getRetrofit("/hearthstone/cards/")

            val call =
                retrofit.create(APIService::class.java)
                    .getCardsByClassAndName(
                        query,
                        classSlug,
                        "standard",
                        "groupByClass:asc,manaCost:asc",
                        1300,
                        "en_US"
                    )

            val cards = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    if (cards != null) {
                        val cardsReceived = cards.cards
                        cardList.clear()
                        cardList.addAll(cardsReceived)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    showError()
                }
                hideKeyboard()
            }
        }
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.Croot.windowToken, 0)
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query)
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText?.length == 0) {
            getClassCards()
        }
        return true
    }

    fun goToEditCards(deckId: Int) {
        val sharedPreferences = getSharedPreferences("userid", Context.MODE_PRIVATE)
        var userId = sharedPreferences.getInt("userid", 0)

        CoroutineScope(Dispatchers.IO).launch {
            val db = TrackstoneDatabase.getInstance(this@SelectCardDeckActivity)
            val class_id = db?.deckDao?.getSlug(deckId, userId)
            val textClass = slugIntToString(class_id)

            runOnUiThread {
                val intent = Intent(this@SelectCardDeckActivity, EditDeckActivity::class.java)
                intent.putExtra("USER_ID", userId)
                intent.putExtra("DECK_ID", deckId)
                intent.putExtra("CLASS_SLUG", textClass.lowercase())
                startActivity(intent)
            }
        }

    }

    fun slugIntToString(id: Int?): String {
        var slug = ""
        when (id) {
            1 -> slug = "DeathKnight"
            2 -> slug = "DemonHunter"
            3 -> slug = "Druid"
            4 -> slug = "Hunter"
            5 -> slug = "Mage"
            6 -> slug = "Paladin"
            7 -> slug = "Priest"
            8 -> slug = "Rogue"
            9 -> slug = "Shaman"
            10 -> slug = "Warlock"
            11 -> slug = "Warrior"
        }
        return slug
    }
}