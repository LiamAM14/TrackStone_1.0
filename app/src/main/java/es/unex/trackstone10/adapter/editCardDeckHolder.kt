package es.unex.trackstone10.adapter

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.unex.trackstone10.AppExecutors
import es.unex.trackstone10.databinding.ItemAddCardDeckBinding
import es.unex.trackstone10.roomdb.Entity.CardEntity
import es.unex.trackstone10.roomdb.Entity.DeckListCardEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class editCardDeckHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemAddCardDeckBinding.bind(view)

    fun render(cards: DeckListCardEntity?, deckId: Int?, context: FragmentActivity?) {
        binding.AddCardDeckButton.text = "Delete Card"
        binding.tvCard.text = cards?.card_name

        Glide.with(binding.ivCard.context).load(cards?.image).into(binding.ivCard)
        binding.AddCardDeckButton.setOnClickListener {
            AppExecutors.instance?.diskIO()?.execute {
                val db = TrackstoneDatabase.getInstance(context)
                if (cards != null && deckId != null) {
                    if (db?.deckListDao?.checkCopies(deckId, cards.card_name!!)!! == 1) {
                        db?.deckListDao?.deleteCardDeck(deckId, cards.card_name)
                        db?.deckDao?.decCount(deckId)
                    } else {
                        db?.deckListDao?.decCopies(deckId, cards.card_name!!)
                        db?.deckDao?.decCount(deckId)
                    }
                }
            }
        }
    }
}