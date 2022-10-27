package es.unex.trackstone10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.adapter.cardAdapter
import es.unex.trackstone10.databinding.ActivityBrowseCardsBinding


class BrowseCardsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityBrowseCardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowseCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCards.adapter =
            cardAdapter(CardProvider.cardsList) { onItemSelected(it) }
    }

    fun onItemSelected(cards: Cards) {
        if (cards.TypeId == 3 && cards.manaCost == 0) {
            intent = Intent(this, Heroe_skinInfoActivity::class.java)
        } else {
            intent = Intent(this, CardInfoActivity::class.java)
        }
        intent.putExtra("CARD_OBJ", cards)
        startActivity(intent)
    }


}