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
import es.unex.trackstone10.API.APIService
import es.unex.trackstone10.API.APIToken
import es.unex.trackstone10.API.CardBackResponse
import es.unex.trackstone10.API.TokenInterceptor
import es.unex.trackstone10.CardInfoActivity
import es.unex.trackstone10.Card_backInfoActivity
import es.unex.trackstone10.R
import es.unex.trackstone10.adapter.cardbackAdapter
import es.unex.trackstone10.databinding.FragmentCardBacksBinding
import es.unex.trackstone10.databinding.FragmentCardsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CardBacksFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentCardBacksBinding
    private lateinit var adapter: cardbackAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var cardbackList = (mutableListOf<CardBackResponse>())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBacksBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.svCard.setOnQueryTextListener(this)
        initRecyclerView()
        APIToken.getToken()
        return view
    }

    private fun initRecyclerView(){
        adapter = cardbackAdapter(cardbackList) { onItemSelected(it) }
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCards.adapter = adapter
    }

    private fun onItemSelected(cardbacks: CardBackResponse){
        val intent: Intent = Intent(activity,Card_backInfoActivity::class.java)
        intent.putExtra("CARD_BACK_OBJ",cardbacks)
        startActivity(intent)

    }

    private fun searchByName(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://us.api.blizzard.com/hearthstone/cardbacks/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            val call = retrofit.create(APIService::class.java).getCardBacksByName("en_US",query)

            val cardbacks = call.body()
            handler.post{
                if(call.isSuccessful){
                    if(cardbacks != null) {
                        val cardbackReceived = cardbacks.cardBacks
                        cardbackList.clear()
                        cardbackList.addAll(cardbackReceived)
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