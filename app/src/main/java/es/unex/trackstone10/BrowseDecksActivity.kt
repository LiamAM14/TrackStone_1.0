package es.unex.trackcstone10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.DeckProvider
import es.unex.trackstone10.adapter.deckAdapter
import es.unex.trackstone10.databinding.ActivityBrowseDecksBinding

class BrowseDecksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBrowseDecksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowseDecksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.recyclerViewDecks.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewDecks.adapter = deckAdapter(DeckProvider.deckList)
    }
}