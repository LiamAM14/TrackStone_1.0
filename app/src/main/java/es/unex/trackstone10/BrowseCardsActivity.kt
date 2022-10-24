package es.unex.trackstone10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.adapter.cardAdapter
import es.unex.trackstone10.adapter.cardHolder

class BrowseCardsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_cards)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerViewCards)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = cardAdapter(CardProvider.cardsList)
    }
}