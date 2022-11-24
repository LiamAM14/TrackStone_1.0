package es.unex.trackstone10

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCardDeckBinding.inflate(layoutInflater)
        val deckId = intent.getIntExtra("DECK_ID", 0)
        AppExecutors.instance?.diskIO()?.execute {
            val db = TrackstoneDatabase.getInstance(this)
            binding.contCards.text = db?.deckDao?.getCountCards(deckId).toString()
        }
        setContentView(binding.root)
        initRecyclerView(deckId)
        getDeckCardRecycler(deckId)
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

    fun onDeletedItem(position: Int, deckId: Int, cards: DeckListCardEntity?) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = TrackstoneDatabase.getInstance(this@EditDeckActivity)
            if (cards != null) {
                if (db?.deckListDao?.checkCopies(deckId, cards.card_name!!)!! == 1) {
                    db.deckListDao?.deleteCardDeck(deckId, cards.card_name)
                    db.deckDao?.decCount(deckId)
                } else {
                    db.deckListDao?.decCopies(deckId, cards.card_name!!)
                    db.deckDao?.decCount(deckId)
                    runOnUiThread {
                        cardList.removeAt(position)
                        adapter.notifyItemRemoved(position)
                    }
                }
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
