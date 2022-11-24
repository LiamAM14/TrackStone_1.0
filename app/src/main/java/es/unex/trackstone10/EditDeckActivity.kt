package es.unex.trackstone10

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.adapter.editCardDeckAdapter
import es.unex.trackstone10.databinding.ActivitySelectCardDeckBinding
import es.unex.trackstone10.roomdb.Entity.DeckListCardEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditDeckActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivitySelectCardDeckBinding
    private lateinit var adapter: editCardDeckAdapter
    private val cardList = (mutableListOf<DeckListCardEntity?>())
    private var deckId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCardDeckBinding.inflate(layoutInflater)
        val deckIdFromIntent = intent.getIntExtra("DECK_ID", 0)
        binding.svCard.setOnQueryTextListener(this)
        deckId = deckIdFromIntent
        AppExecutors.instance?.diskIO()?.execute {
            val db = TrackstoneDatabase.getInstance(this)
            val count = db?.deckDao?.getCountCards(deckId).toString()
            binding.contCards.text = "CARD COUNT: $count/30"
        }
        setContentView(binding.root)
        initRecyclerView(deckId)
        getDeckCardRecycler(deckId)
        binding.buttonCreateDeck.setOnClickListener {
            goToAddCards(deckId)
        }
        binding.buttonFinishDeck.setOnClickListener {
            val intent = Intent(this, ButtonNavigationMenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView(deckId: Int) {
        adapter = editCardDeckAdapter(
            cardsList = cardList,
            onClickDeleted = { onDeletedItem(it, deckId, cardList[it]) },
            deckId = deckId,
            conText = this
        )
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCards.adapter = adapter
    }

    private fun getDeckCardRecycler(deckId: Int) {
        AppExecutors.instance?.diskIO()?.execute {
            val db = TrackstoneDatabase.getInstance(this)
            val cards = db?.deckListDao?.getAllByDeckId(deckId)
            if (cards != null) {
                cardList.clear()
                cardList.addAll(cards)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun getDeckCardByName(query: String) {
        AppExecutors.instance?.diskIO()?.execute {
            val db = TrackstoneDatabase.getInstance(this)
            val queryPercentage = "%$query%"
            val cards = db?.deckListDao?.getCardsByName(queryPercentage)
            runOnUiThread {
                if (cards != null) {
                    cardList.clear()
                    cardList.addAll(cards)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    fun onDeletedItem(position: Int, deckId: Int, cards: DeckListCardEntity?) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = TrackstoneDatabase.getInstance(this@EditDeckActivity)
            if (cards != null) {
                if (db?.deckListDao?.checkCopies(deckId, cards.card_name!!)!! == 1) {
                    db.deckListDao?.deleteCardDeck(deckId, cards.card_name)
                    db.deckDao?.decCount(deckId)
                    runOnUiThread {
                        cardList.removeAt(position)
                        adapter.notifyItemRemoved(position)
                    }
                } else {
                    db.deckListDao?.decCopies(deckId, cards.card_name!!)
                    db.deckDao?.decCount(deckId)
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            getDeckCardByName(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText?.length == 0) {
            getDeckCardRecycler(deckId)
        }
        return true
    }

    fun goToAddCards(deckId: Int) {
        val sharedPreferences = getSharedPreferences("userid", Context.MODE_PRIVATE)
        var userId = sharedPreferences.getInt("userid", 0)

        CoroutineScope(Dispatchers.IO).launch {
            val db = TrackstoneDatabase.getInstance(this@EditDeckActivity)
            val class_id = db?.deckDao?.getSlug(deckId, userId)
            val textClass = slugIntToString(class_id)

            runOnUiThread {
                val intent = Intent(this@EditDeckActivity, SelectCardDeckActivity::class.java)
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
