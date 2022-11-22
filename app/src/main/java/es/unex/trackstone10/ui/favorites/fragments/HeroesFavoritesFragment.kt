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
import es.unex.trackstone10.HeroInfoActivity
import es.unex.trackstone10.adapter.HeroFavAdapter
import es.unex.trackstone10.databinding.FragmentHeroesBinding
import es.unex.trackstone10.roomdb.Entity.ClassEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroesFavoritesFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentHeroesBinding
    private lateinit var adapter: HeroFavAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var heroList = (mutableListOf<ClassEntity?>())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroesBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.svCard.setOnQueryTextListener(this)
        initRecyclerView()
        getHeroReclycer()
        return view
    }

    private fun initRecyclerView(){
        adapter = HeroFavAdapter(heroList) { onItemSelected(it) }
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCards.adapter = adapter
    }

    private fun onItemSelected(heroes: ClassEntity){
        val intent: Intent = Intent(activity, HeroInfoActivity::class.java)
        intent.putExtra("CARD_OBJ", heroes)
        startActivity(intent)
    }

    private fun getHeroReclycer(){
        CoroutineScope(Dispatchers.IO).launch {
            val db = TrackstoneDatabase.getInstance(activity)
            val hero = db?.classDao?.getAll()
            handler.post{
                if(hero != null){
                    heroList.clear()
                    heroList.addAll(hero)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun searchByName(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val db = TrackstoneDatabase.getInstance(activity)
            val query2 = "$query%"
            val hero = db?.classDao?.getByName(query2)
            handler.post{
                if(hero != null){
                    heroList.clear()
                    heroList.addAll(hero)
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