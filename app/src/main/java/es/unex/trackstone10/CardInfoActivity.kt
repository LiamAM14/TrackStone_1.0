package es.unex.trackstone10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.databinding.ActivityCardInfoBinding
import es.unex.trackstone10.roomdb.Entity.CardEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class CardInfoActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityCardInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cards = intent.getSerializableExtra("CARD_OBJ") as CardResponse
        binding.cardtitle.text = cards.name
        binding.cardDetail1.text = cards.flavorText
        when (cards.rarityId){
            1 -> binding.cardDetail2.text = "Common"
            2 -> binding.cardDetail2.text = "Free"
            3 -> binding.cardDetail2.text = "Rare"
            4 -> binding.cardDetail2.text = "Epic"
            5 -> binding.cardDetail2.text = "Legendary"
        }
        when (cards.cardTypeId){
            3 -> binding.cardDetail3.text = "Heroe"
            4 -> binding.cardDetail3.text = "Minion"
            5 -> binding.cardDetail3.text = "Spell"
            7 -> binding.cardDetail3.text = "Weapon"
            39 -> binding.cardDetail3.text = "Location"
        }
        binding.cardDetail4.text = "Artist: ${cards.artistName}"
        Glide.with(binding.cardDetails.context).load(cards.image).into(binding.cardDetails)


        binding.addDeleteFavoriteButton.setOnClickListener{
            AppExecutors.instance?.diskIO()?.execute{
                val db = TrackstoneDatabase.getInstance(this)
                var race: String = ""
                if(cards.cardTypeId == 4 ||cards.cardTypeId == 5) {
                    when(cards.cardTypeId){
                        4 -> when(cards.spellSchoolId){
                            1 -> race = "Arcane"
                            2 -> race = "Fire"
                            3 -> race = "Frost"
                            4 -> race = "Nature"
                            5 -> race = "Holy"
                            6 -> race = "Shadow"
                            7 -> race = "Fel"
                        }
                        5 -> when(cards.minionTypeId){
                            1 -> race = "Blood elf"
                            2 -> race = "Draenei"
                            3 -> race = "Dwarf"
                            4 -> race = "Human"
                            6 -> race = "Night elf"
                            7 -> race = "Orc"
                            8 -> race = "Tauren"
                            9 -> race = "Troll"
                            10 -> race = "Undead"
                            14 -> race = "Murloc"
                            15 -> race = "Demon"
                            17 -> race = "Mech"
                            18 -> race = "Elemental"
                            20 -> race = "Beast"
                            21 -> race = "Totem"
                            23 -> race = "Pirate"
                            24 -> race = "Dragon"
                            26 -> race = "All"
                            43 -> race = "Quilboar"
                            88 -> race = "Half-Orc"
                            92 -> race = "Naga"
                            93 -> race = "Old God"
                            94 -> race = "Pandaren"
                            95 -> race = "Gronn"
                        }
                    }
                }
                db?.carddao?.insert(
                    CardEntity(
                        cards.name,
                        cards.rarityId,
                        cards.classId,
                        cards.manaCost,
                        cards.image,
                        binding.cardDetail3.text.toString(),
                        race
                    )
                )
                val card = db?.carddao?.getAll()

            }

        }
    }

}