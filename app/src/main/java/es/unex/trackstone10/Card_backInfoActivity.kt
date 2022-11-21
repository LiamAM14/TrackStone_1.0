package es.unex.trackstone10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import es.unex.trackstone10.API.CardBackResponse
import es.unex.trackstone10.databinding.ActivityCardBackInfoBinding
import es.unex.trackstone10.roomdb.Entity.CardBackEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Card_backInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardBackInfoBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBackInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cardBacks = intent.getSerializableExtra("CARD_BACK_OBJ") as CardBackResponse
        binding.cardBackDetailsName.text = cardBacks.name
        binding.cardBackDetail1.text = cardBacks.text
        Glide.with(binding.cardBackDetails.context).load(cardBacks.image).into(binding.cardBackDetails)

        binding.addCardBackFavorite.setOnClickListener {
            AppExecutors.instance?.diskIO()?.execute {
                val db = TrackstoneDatabase.getInstance(this)
                db?.cardbackdao?.insert(
                    CardBackEntity(
                        cardBacks.name,
                        cardBacks.image
                    )
                )

            }
        }

    }
}