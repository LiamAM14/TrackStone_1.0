package es.unex.trackstone10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.API.APIrest
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.API.CardResponseList
import es.unex.trackstone10.adapter.cardAdapter
import es.unex.trackstone10.databinding.ActivityBrowseCardsBinding

class BrowseCardsActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityBrowseCardsBinding
    private lateinit var adapter: cardAdapter
    private lateinit var cardList: CardResponseList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowseCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svCard.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = cardAdapter(cardList) { onItemSelected(it) }
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCards.adapter = adapter
    }

    private fun onItemSelected(cards: CardResponse) {
        if (cards.cardTypeId == 3 && cards.cardSetId == 17) {
            intent = Intent(this, Heroe_skinInfoActivity::class.java)
        } else {
            intent = Intent(this, CardInfoActivity::class.java)
        }
        intent.putExtra("CARD_OBJ", cards)
        startActivity(intent)
    }

//    private fun searchByName(query: String) {
//        runOnUiThread {
//            if (APIrest.getCards(query) == 2) {
//                cardList = APIrest.cards!!
//                adapter.notifyDataSetChanged()
//            } else {
//                showError()
//            }
//        }
//    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            //searchByName(query)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }


}