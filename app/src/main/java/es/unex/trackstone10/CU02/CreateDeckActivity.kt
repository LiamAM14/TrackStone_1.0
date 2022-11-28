package es.unex.trackstone10.CU02

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import es.unex.trackstone10.AppExecutors
import es.unex.trackstone10.R
import es.unex.trackstone10.databinding.ActivityCreateDeckBinding
import es.unex.trackstone10.roomdb.Entity.DeckEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class CreateDeckActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateDeckBinding
    lateinit var option: Spinner
    private lateinit var textClass:String
    var deckId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateDeckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("userid", Context.MODE_PRIVATE)
        var userId = sharedPreferences.getInt("userid", 0)

        val lista = listOf(
            "DeathKnight",
            "DemonHunter",
            "Druid",
            "Hunter",
            "Mage",
            "Paladin",
            "Priest",
            "Rogue",
            "Shaman",
            "Warlock",
            "Warrior"
        )
        option = findViewById(R.id.mySpinner)
        var num: Int = 0

        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        option.adapter = adaptador

        option.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                textClass = option.selectedItem.toString()
                num = stringToInt(textClass)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.addCardButton.setOnClickListener {
            AppExecutors.instance?.diskIO()?.execute {
                val db = TrackstoneDatabase.getInstance(this)
                db?.deckDao?.insert(
                    DeckEntity(
                        num,
                        binding.editTextTextPersonName.text.toString(),
                        0,
                        userId,

                    )
                )
                var deck = db?.deckDao?.getEntity(binding.editTextTextPersonName.text.toString())
                if(deck?.id != null) {
                    deckId = deck.id
                }
                val intent = Intent(this, SelectCardDeckActivity::class.java)
                intent.putExtra("USER_ID", userId)
                intent.putExtra("DECK_ID", deckId)
                intent.putExtra("CLASS_SLUG", textClass.lowercase())
                startActivity(intent)
            }

        }
    }

    fun stringToInt(className: String): Int {
        var num: Int = 0
        when (className) {
            "DeathKnight" -> num = 1
            "DemonHunter" -> num = 2
            "Druid" -> num = 3
            "Hunter" -> num = 4
            "Mage" -> num = 5
            "Paladin" -> num = 6
            "Priest" -> num = 7
            "Rogue" -> num = 8
            "Shaman" -> num = 9
            "Warlock" -> num = 10
            "Warrior" -> num = 11
        }
        return num
    }

}