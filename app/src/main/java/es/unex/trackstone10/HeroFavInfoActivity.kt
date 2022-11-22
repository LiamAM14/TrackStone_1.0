package es.unex.trackstone10

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import es.unex.trackstone10.databinding.ActivityHeroeSkinInfoBinding
import es.unex.trackstone10.roomdb.Entity.ClassEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class HeroFavInfoActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityHeroeSkinInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroeSkinInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val heroes = intent.getSerializableExtra("CARD_OBJ") as ClassEntity
        binding.heroeSkinDetailsName.text = heroes.name
        Glide.with(binding.heroeSkinDetails.context).load(heroes.url).into(binding.heroeSkinDetails)

        binding.addHeroeSkinFavorite.text = "Delete from Favorites"

        binding.addHeroeSkinFavorite.setOnClickListener {
            AppExecutors.instance?.diskIO()?.execute {
                val db = TrackstoneDatabase.getInstance(this)
                db?.classDao?.deleteFromId(heroes.id)
            }
            val intent = Intent(this, ButtonNavigationMenuActivity::class.java)
            startActivity(intent)
        }
    }

}