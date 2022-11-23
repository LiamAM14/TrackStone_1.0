package es.unex.trackstone10.adapter

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.AppExecutors
import es.unex.trackstone10.databinding.ItemAddCardDeckBinding
import es.unex.trackstone10.roomdb.Entity.DeckListCardEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class cardAddDeckHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemAddCardDeckBinding.bind(view)


    fun render(cards: CardResponse, id: Int?, user: Int?, context: FragmentActivity?) {
        binding.tvCard.text = cards.name

        Glide.with(binding.ivCard.context).load(cards.image).into(binding.ivCard)
        binding.AddCardDeckButton.setOnClickListener {
            AppExecutors.instance?.diskIO()?.execute {
                if (cards != null && id != null) {
                    val db = TrackstoneDatabase.getInstance(context)
                    if (db?.deckListDao?.checkCard(id, cards.name!!) != null) {
                        if (db?.deckListDao?.checkCopies(id, cards.name!!)!! == 1) {
                            db?.deckListDao?.incCopies(id, cards.name!!)
                        } else {
                            //error
                        }
                    } else {
                        db?.deckListDao?.insert(
                            DeckListCardEntity(
                                id,
                                user,
                                cards.name,
                                0,
                                cards.rarityId,
                                cards.classId,
                                cards.manaCost,
                                cards.image
                            )
                        )
                    }
                }
            }
        }
    }
}
