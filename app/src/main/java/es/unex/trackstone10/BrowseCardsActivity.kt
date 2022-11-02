package es.unex.trackstone10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import es.unex.trackstone10.adapter.cardAdapter
import es.unex.trackstone10.databinding.ActivityBrowseCardsBinding
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class BrowseCardsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityBrowseCardsBinding
    private lateinit var adapter: cardAdapter
    private val CardList = mutableListOf<CardResponse>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowseCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = cardAdapter(CardList) { onItemSelected(it) }
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCards.adapter = adapter
    }

    fun onItemSelected(cards: CardResponse) {
        if (cards.cardTypeId == 3 && cards.cardSetId == 17) {
            intent = Intent(this, Heroe_skinInfoActivity::class.java)
        } else {
            intent = Intent(this, CardInfoActivity::class.java)
        }
        intent.putExtra("CARD_OBJ", cards)
        startActivity(intent)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://us.api.blizzard.com/hearthstone/cards?locale=en_US")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun searchByName(query: String){
        CoroutineScope(Dispatchers.IO).launch{
            val call = getRetrofit().create(APIService::class.java).getCardsByName("&textFilter=$query&access_token=EUD1fhUTUevUg8m4uWoJsRwnhT0Vs6pqpe")
            val card: CardResponse? = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    if(card != null) {
                        CardList.clear()
                        CardList.add(card)
                        adapter.notifyDataSetChanged()
                    }
                    //Show recyclerview
                }else{
                    showError()
                }
            }
        }
    }

    private fun showError(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }


}