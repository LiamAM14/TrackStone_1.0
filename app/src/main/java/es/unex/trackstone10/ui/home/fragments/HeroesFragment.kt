package es.unex.trackstone10.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.API.APIrest
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.API.CardResponseList
import es.unex.trackstone10.CardInfoActivity
import es.unex.trackstone10.Heroe_skinInfoActivity
import es.unex.trackstone10.adapter.cardAdapter
import es.unex.trackstone10.databinding.FragmentHeroesBinding

class HeroesFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentHeroesBinding
    private lateinit var adapter: cardAdapter
    private var heroList: CardResponseList? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroesBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.svCard.setOnQueryTextListener(this)
        initRecyclerView()
        return view
    }

    private fun initRecyclerView() {
        adapter = cardAdapter(heroList) { onItemSelected(it) }
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCards.adapter = adapter
    }

    private fun onItemSelected(cards: CardResponse) {
        val intent: Intent
        if (cards.cardTypeId == 3 && cards.cardSetId == 17) {
            intent = Intent(activity, Heroe_skinInfoActivity::class.java)
            activity?.startActivity(intent)
        } else {
            intent = Intent(activity, CardInfoActivity::class.java)
        }
        intent.putExtra("CARD_OBJ", cards)
        startActivity(intent)
    }

//    private fun searchByName(query: String) {
//        APIrest.getHeroes(query)
//        if (APIrest.heroes != null) {
//            heroList = APIrest.heroes
//            adapter.notifyDataSetChanged()
//        } else {
//            showError()
//        }
//    }

    private fun showError() {
        Toast.makeText(activity, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            //searchByName(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}