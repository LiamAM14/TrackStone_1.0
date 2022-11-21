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
import es.unex.trackstone10.Card_backInfoActivity
import es.unex.trackstone10.R
import es.unex.trackstone10.adapter.cardbackAdapterFav
import es.unex.trackstone10.databinding.FragmentCardBacksBinding
import es.unex.trackstone10.roomdb.Entity.CardBackEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardBacksFavoritesFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentCardBacksBinding
    private lateinit var adapter: cardbackAdapterFav
    private val handler = Handler(Looper.getMainLooper())
    private var cardBackList = (mutableListOf<CardBackEntity?>())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBacksBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.svCard.setOnQueryTextListener(this)
        initRecyclerView()
        getCardBackFav()

        return view
    }

    private fun initRecyclerView(){
        adapter = cardbackAdapterFav(cardBackList) { onItemSelected((it))}
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCards.adapter = adapter
    }

    private fun onItemSelected(cardbacks: CardBackEntity) {
        val intent: Intent = Intent(activity, Card_backInfoActivity::class.java)
        intent.putExtra("CARD_BACK_OBJ", cardbacks)
        startActivity(intent)
    }

    private fun getCardBackFav(){
        CoroutineScope(Dispatchers.IO).launch {
            val db = TrackstoneDatabase.getInstance(activity)
            val cardback = db?.cardbackdao?.getAll()
            handler.post{
                if(cardback != null){
                    cardBackList.clear()
                    cardBackList.addAll(cardback)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun searchByName(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val db = TrackstoneDatabase.getInstance(activity)
            val query2 = "%$query%"
            val cardback = db?.cardbackdao?.getByName(query2)
            handler.post{
                if(cardback != null){
                    cardBackList.clear()
                    cardBackList.addAll(cardback)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?):Boolean{
        if(!query.isNullOrEmpty()){
            searchByName(query)
        }
        return true
    }

    override fun onQueryTextChange(newText:String?): Boolean{
        if(newText?.length == 0){getCardBackFav()}
        return true
    }
}