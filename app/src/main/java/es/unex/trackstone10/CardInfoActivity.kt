package es.unex.trackstone10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.databinding.ActivityCardInfoBinding

class CardInfoActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityCardInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cards = intent.getSerializableExtra("CARD_OBJ") as CardResponse
        binding.cardDetailsName.text = cards.name
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
    }

}