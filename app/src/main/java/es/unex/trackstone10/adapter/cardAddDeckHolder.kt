package es.unex.trackstone10.adapter

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.AppExecutors
import es.unex.trackstone10.databinding.ItemAddCardDeckBinding
import es.unex.trackstone10.roomdb.Entity.DeckListCardEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class cardAddDeckHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemAddCardDeckBinding.bind(view)


    fun render(cards: CardResponse, id: Int?, user: Int?, context: FragmentActivity?) {
        binding.tvCard.text = cards.name

        Glide.with(binding.ivCard.context).load(cards.image).into(binding.ivCard)
        binding.AddCardDeckButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val db = TrackstoneDatabase.getInstance(context)
                if(db?.deckDao?.getCountCards(id)!! < 5) {
                    if (cards != null && id != null) {
                        val check = db?.deckListDao?.checkCard(id, cards.name!!)
                        if (check?.size != 0) {
                            if (db.deckListDao?.checkCopies(id, cards.name!!)!! == 1) {
                                db.deckListDao?.incCopies(id, cards.name!!)
                                db.deckDao?.AddingCards(id)
                            } else {
                                //error
                            }
                        } else {
                            db.deckListDao?.insert(
                                DeckListCardEntity(
                                    id,
                                    user,
                                    cards.name,
                                    1,
                                    cards.rarityId,
                                    cards.classId,
                                    cards.manaCost,
                                    cards.image
                                )
                            )
                            db?.deckDao?.AddingCards(id)
                        }
                    }
                }
                else{
                    //error
                }
            }
        }
    }
}
