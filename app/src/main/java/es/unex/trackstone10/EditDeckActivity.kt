package es.unex.trackstone10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.adapter.editCardDeckAdapter
import es.unex.trackstone10.databinding.ActivitySelectCardDeckBinding
import es.unex.trackstone10.roomdb.Entity.DeckListCardEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class EditDeckActivity: AppCompatActivity(), SearchView.OnQueryTextListener  {

    private lateinit var binding: ActivitySelectCardDeckBinding
    private lateinit var adapter: editCardDeckAdapter
    private val cardList = (mutableListOf<DeckListCardEntity?>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCardDeckBinding.inflate(layoutInflater)
        var deckId = intent.getIntExtra("DECK_ID",0)
        AppExecutors.instance?.diskIO()?.execute {
            var db = TrackstoneDatabase.getInstance(this)
            binding.contCards.text = db?.deckDao?.getCountCards(deckId).toString()
        }
        setContentView(binding.root)
        initRecyclerView(deckId)
        getDeckCardRecycler(deckId)
    }

    private fun initRecyclerView(deckId:Int) {
        adapter = editCardDeckAdapter(cardList, deckId, this)
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCards.adapter = adapter
    }

    private fun getDeckCardRecycler(deckId:Int) {
        AppExecutors.instance?.diskIO()?.execute {
            var db = TrackstoneDatabase.getInstance(this)
            val cards = db?.deckListDao?.getAllByDeckId(deckId)
            if(cards != null) {
                cardList.clear()
                cardList.addAll(cards)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}