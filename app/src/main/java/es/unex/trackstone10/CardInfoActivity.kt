package es.unex.trackstone10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import es.unex.trackstone10.databinding.ActivityCardInfoBinding

class CardInfoActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityCardInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cards = intent.getSerializableExtra("CARD_OBJ") as Cards
        binding.cardDetailsName.text = cards.Name
        Glide.with(binding.cardDetails.context).load(cards.Image).into(binding.cardDetails)
    }

}