package es.unex.trackstone10.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import es.unex.trackstone10.AppExecutors
import es.unex.trackstone10.Deck
import es.unex.trackstone10.R
import es.unex.trackstone10.databinding.ItemDeckBinding
import es.unex.trackstone10.roomdb.Entity.DeckEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class deckHolder(view: View): ViewHolder(view) {

    val binding = ItemDeckBinding.bind(view)

   fun render(deck: DeckEntity? ){
       binding.deckName.text = deck?.name
       when(deck?.classid){
           1 -> binding.imageViewClassDeck.setImageResource(R.drawable.deathknight)
           2 -> binding.imageViewClassDeck.setImageResource(R.drawable.demonhunter)
           3 -> binding.imageViewClassDeck.setImageResource(R.drawable.druid)
           4 -> binding.imageViewClassDeck.setImageResource(R.drawable.hunter)
           5 -> binding.imageViewClassDeck.setImageResource(R.drawable.mage)
           6 -> binding.imageViewClassDeck.setImageResource(R.drawable.paladin)
           7 -> binding.imageViewClassDeck.setImageResource(R.drawable.priest)
           8 -> binding.imageViewClassDeck.setImageResource(R.drawable.rogue)
           9 -> binding.imageViewClassDeck.setImageResource(R.drawable.shaman)
           10 -> binding.imageViewClassDeck.setImageResource(R.drawable.warlock)
           11 -> binding.imageViewClassDeck.setImageResource(R.drawable.warrior)
       }
   }

}