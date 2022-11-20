package es.unex.trackstone10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import es.unex.trackstone10.API.CardBackResponse
import es.unex.trackstone10.databinding.ActivityCardBackInfoBinding

class Card_backInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardBackInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBackInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cardBacks = intent.getSerializableExtra("CARD_BACK_OBJ") as CardBackResponse
        binding.cardBackDetailsName.text = cardBacks.name
        binding.cardBackDetail1.text = cardBacks.text
        Glide.with(binding.cardBackDetails.context).load(cardBacks.image).into(binding.cardBackDetails)

    }
}