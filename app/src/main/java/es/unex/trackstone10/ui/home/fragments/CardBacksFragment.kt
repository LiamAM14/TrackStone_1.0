package es.unex.trackstone10.ui.home.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.API.APIService
import es.unex.trackstone10.API.APIToken
import es.unex.trackstone10.API.CardBackResponse
import es.unex.trackstone10.CU04.CardBackInfoActivity
import es.unex.trackstone10.adapter.cardbackAdapter
import es.unex.trackstone10.databinding.FragmentCardBacksBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        getCardBacks()
        return view
    }

    private fun initRecyclerView(){
        adapter = cardbackAdapter(cardbackList) { onItemSelected(it) }
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCards.adapter = adapter
    }

    private fun onItemSelected(cardbacks: CardBackResponse){
        val intent: Intent = Intent(activity, CardBackInfoActivity::class.java)
        intent.putExtra("CARD_BACK_OBJ",cardbacks)
        startActivity(intent)

    }

    private fun getCardBacks(){
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)

            val retrofit = APIToken.getRetrofit("/hearthstone/cardbacks/")

            val call = retrofit.create(APIService::class.java).getCardBacksByName("en_US")

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

    private fun searchByName(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = APIToken.getRetrofit("/hearthstone/cardbacks/")

            val call = retrofit.create(APIService::class.java).getCardBacksByName("en_US", query)

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
                hideKeyboard()            }
        }
    }

    private fun hideKeyboard() {
            val imm = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.CBroot.windowToken, 0)
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
        if(newText?.length == 0){getCardBacks()}

        return true
    }

}