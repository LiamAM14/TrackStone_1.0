package es.unex.trackstone10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.adapter.cardAdapter
import es.unex.trackstone10.adapter.cardHolder
import es.unex.trackstone10.databinding.ActivityBrowseCardsBinding
import es.unex.trackstone10.databinding.ActivityMainBinding

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

    fun onItemSelected( cards: Cards){
        intent = Intent(this, CardInfoActivity::class.java)
        startActivity(intent)
    }


}