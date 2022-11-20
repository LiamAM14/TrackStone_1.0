package es.unex.trackstone10.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import es.unex.trackstone10.API.*
import es.unex.trackstone10.CardInfoActivity
import es.unex.trackstone10.Heroe_skinInfoActivity
import es.unex.trackstone10.adapter.cardAdapter
import es.unex.trackstone10.databinding.FragmentHeroesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroesFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentHeroesBinding
    private lateinit var adapter: cardAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var heroList = (mutableListOf<CardResponse>())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroesBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.svCard.setOnQueryTextListener(this)
        initRecyclerView()
        APIToken.getToken()
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

    private fun searchByName(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://us.api.blizzard.com/hearthstone/cards/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()


            val  call = retrofit.create(APIService::class.java).getHeroByName(query,"en_US")

            val heroes = call.body()
            handler.post{
                if(call.isSuccessful) {
                    if (heroes != null) {
                        val cardsReceived = heroes.cards
                        heroList.clear()
                        heroList.addAll(cardsReceived)
                        adapter.notifyDataSetChanged()
                    }
                } else{
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(activity, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
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