package es.unex.trackstone10.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import es.unex.trackstone10.Deck
import es.unex.trackstone10.databinding.ItemDeckBinding

class deckHolder(view: View): ViewHolder(view) {

    val binding = ItemDeckBinding.bind(view)

   fun render(deck: Deck ){
       binding.deckName.text = deck.name
       Glide.with(binding.imageViewClassDeck).load(deck.dClass.image).into(binding.imageViewClassDeck)
   }

}