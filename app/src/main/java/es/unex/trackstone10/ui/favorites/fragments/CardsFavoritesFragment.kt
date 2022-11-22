package es.unex.trackstone10.ui.favorites.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.API.APIToken
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.AppExecutors
import es.unex.trackstone10.CardFavInfoActivity
import es.unex.trackstone10.CardInfoActivity
import es.unex.trackstone10.adapter.cardAdapter
import es.unex.trackstone10.adapter.cardAdapterFav
import es.unex.trackstone10.databinding.FragmentCardsBinding
import es.unex.trackstone10.roomdb.Entity.CardEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardsFavoritesFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentCardsBinding
    private lateinit var adapter: cardAdapterFav
    private val handler = Handler(Looper.getMainLooper())
    private var cardList = (mutableListOf<CardEntity?>())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardsBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.svCard.setOnQueryTextListener(this)
        initRecyclerView()
        getCardsRecycler()
        return view
    }

    private fun initRecyclerView() {
        adapter = cardAdapterFav(cardList) { onItemSelected(it) }
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCards.adapter = adapter
    }

    private fun onItemSelected(cards: CardEntity) {
        val intent: Intent = Intent(activity, CardFavInfoActivity::class.java)
        intent.putExtra("CARD_OBJ", cards)
        startActivity(intent)
    }

    private fun getCardsRecycler() {
        CoroutineScope(Dispatchers.IO).launch {
            val db = TrackstoneDatabase.getInstance(activity)
            val card = db?.carddao?.getAll()
            handler.post {
                if (card != null) {
                    cardList.clear()
                    cardList.addAll(card)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun searchByName(query:String) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = TrackstoneDatabase.getInstance(activity)
            val query2 = "%$query%"
            val card = db?.carddao?.getByName(query2)
            handler.post {
                if (card != null) {
                    cardList.clear()
                    cardList.addAll(card)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}