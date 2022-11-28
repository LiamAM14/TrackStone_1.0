package es.unex.trackstone10

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import es.unex.trackstone10.databinding.ActivityCardBackInfoBinding
import es.unex.trackstone10.roomdb.Entity.CardBackEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class CardBackFavInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardBackInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBackInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cardbacks = intent.getSerializableExtra("CARD_BACK_OBJ") as CardBackEntity
        binding.cardBackDetailsName.text = cardbacks.name
        binding.cardBackDetail1.text = ""
        Glide.with(binding.cardBackDetails.context).load(cardbacks.url).into(binding.cardBackDetails)

        binding.addCardBackFavorite.text = "Delete from Favorites"

        binding.addCardBackFavorite.setOnClickListener {
            AppExecutors.instance?.diskIO()?.execute {
                val db = TrackstoneDatabase.getInstance(this)
                db?.cardbackdao?.deleteFromId(cardbacks.id)
            }
            this.finish()
        }
    }
}