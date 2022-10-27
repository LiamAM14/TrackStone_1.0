package es.unex.trackstone10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import es.unex.trackstone10.databinding.ActivityHeroeSkinInfoBinding

class Heroe_skinInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroeSkinInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroeSkinInfoBinding.inflate(layoutInflater)
        val cards = intent.getSerializableExtra("CARD_OBJ") as Cards
        setContentView(binding.root)
        Glide.with(binding.heroeSkinDetails).load(cards.Image).into(binding.heroeSkinDetails)
        binding.heroeSkinDetailsName.text = cards.Name
    }
}