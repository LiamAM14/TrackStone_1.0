package es.unex.trackstone10

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import es.unex.trackstone10.API.*
import es.unex.trackstone10.adapter.cardAdapter
import es.unex.trackstone10.databinding.ActivitySelectCardDeckBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectCardDeck : AppCompatActivity() {
    private lateinit var binding: ActivitySelectCardDeckBinding
    private lateinit var adapter: cardAdapter
    private var cardList = (mutableListOf<CardResponse>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_card_deck)
    }


//    private fun initRecyclerView() {
//        adapter = cardAdapter(cardList) { onItemSelected(it) }
//        binding.recyclerViewCards.layoutManager = LinearLayoutManager(this)
//        binding.recyclerViewCards.adapter = adapter
//    }

    private fun getClassCards() {
        CoroutineScope(Dispatchers.IO).launch {
            val classSelected = intent.getStringExtra("CLASS_SLUG")
            val classSlug = "$classSelected,neutral"

            val retrofit = APIToken.getRetrofit("/hearthstone/cards/")
            val cards: CardResponseList?


            val call = retrofit.create(APIService::class.java)
                .getCardsByClass(classSlug, "standard", "manaCost:asc", 1300, "en_US")
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

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.SCroot.windowToken, 0)
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}