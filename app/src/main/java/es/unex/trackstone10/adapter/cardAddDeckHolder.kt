package es.unex.trackstone10.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.AppExecutors
import es.unex.trackstone10.databinding.ItemAddCardDeckBinding
import es.unex.trackstone10.roomdb.Entity.DeckListCardEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class cardAddDeckHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemAddCardDeckBinding.bind(view)


    //val sharedPreferences = getSharedPreferences("userid", Context.MODE_PRIVATE)
    //var userid = sharedPreferences.getInt("userid", 0)

    fun render(cards: CardResponse) {
        binding.tvCard.text = cards.name

        Glide.with(binding.ivCard.context).load(cards.image).into(binding.ivCard)
//        binding.AddCardDeckButton.setOnClickListener {
//            AppExecutors.instance?.diskIO()?.execute {
//                val db = TrackstoneDatabase.getInstance(this)
//                db?.deckListDao?.insert(
//                    DeckListCardEntity(
//                        cards.id,
//                        cards.classId,
//                        cards.name,
//                        0,
//                        cards.rarityId,
//                        cards.classId,
//                        cards.manaCost,
//                        cards.image
//                    )
//                )
//            }
//        }
    }
}
