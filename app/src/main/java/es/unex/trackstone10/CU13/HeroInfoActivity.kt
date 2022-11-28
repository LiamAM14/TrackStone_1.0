package es.unex.trackstone10.CU13

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.AppExecutors
import es.unex.trackstone10.ButtonNavigationMenuActivity
import es.unex.trackstone10.databinding.ActivityHeroeSkinInfoBinding
import es.unex.trackstone10.roomdb.Entity.ClassEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class HeroInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroeSkinInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroeSkinInfoBinding.inflate(layoutInflater)
        val cards = intent.getSerializableExtra("CARD_OBJ") as CardResponse
        setContentView(binding.root)
        Glide.with(binding.heroeSkinDetails).load(cards.image).into(binding.heroeSkinDetails)
        binding.heroeSkinDetailsName.text = cards.name
        binding.textViewArtist.text = "Artist: ${cards.artistName}"

        val sharedPreferences = getSharedPreferences("userid", Context.MODE_PRIVATE)
        var userId = sharedPreferences.getInt("userid", 0)

        binding.addHeroeSkinFavorite.setOnClickListener{
            AppExecutors.instance?.diskIO()?.execute{
                val db = TrackstoneDatabase.getInstance(this)
                db?.classDao?.insert(
                    ClassEntity(
                        cards.name,
                        cards.classId,
                        cards.image,
                        userId
                    )
                )
            }
            val intent = Intent(this, ButtonNavigationMenuActivity::class.java)
            startActivity(intent)
        }
    }
}