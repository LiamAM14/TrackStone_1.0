package es.unex.trackstone10

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.API.*
import es.unex.trackstone10.adapter.cardAddDeckAdapter
import es.unex.trackstone10.databinding.ActivitySelectCardDeckBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectCardDeckActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivitySelectCardDeckBinding
    private lateinit var adapter: cardAddDeckAdapter
    private var cardList = (mutableListOf<CardResponse>())
    private var classSelected:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCardDeckBinding.inflate((layoutInflater))
        var deckId = intent.getIntExtra("DECK_ID",0)
        var userId = intent.getIntExtra("USER_ID",0)
        setContentView(binding.root)
        initRecyclerView(deckId,userId)
        getClassCards()

    }


    private fun initRecyclerView(deckId:Int?,userId:Int?) {
        adapter = cardAddDeckAdapter(cardList,deckId, userId,this)
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
                .getCardsByClass(classSlug, "standard", "groupByClass:asc,manaCost:asc", 1300, "en_US")
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
                    .getCardsByClassAndName(query,classSlug, "standard", "groupByClass:asc,manaCost:asc",1300, "en_US")

            val cards = call.body()
                runOnUiThread{
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
        if(newText?.length == 0) {
            getClassCards()
        }
        return true
    }
}