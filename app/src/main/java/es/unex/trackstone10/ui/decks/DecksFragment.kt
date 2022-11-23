package es.unex.trackstone10.ui.decks

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.*
import es.unex.trackstone10.adapter.HeroFavAdapter
import es.unex.trackstone10.adapter.deckAdapter
import es.unex.trackstone10.databinding.FragmentDecksBinding
import es.unex.trackstone10.roomdb.Entity.DeckEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase


class DecksFragment : Fragment() {

    private lateinit var binding: FragmentDecksBinding
    private lateinit var adapter: deckAdapter
    private val deckList = (mutableListOf<DeckEntity?>())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDecksBinding.inflate(inflater,container,false)
        val view = binding.root
        initRecyclerView()
        binding.buttonCreateDeck.setOnClickListener{
            val intent = Intent(activity,CreateDeckActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    private fun initRecyclerView(){
        adapter = deckAdapter(deckList)
        binding.recyclerViewDecks.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewDecks.adapter = adapter
    }


}