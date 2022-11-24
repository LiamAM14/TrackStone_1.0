package es.unex.trackstone10.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.unex.trackstone10.databinding.ItemAddCardDeckBinding
import es.unex.trackstone10.roomdb.Entity.DeckListCardEntity

class editCardDeckHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemAddCardDeckBinding.bind(view)

    fun render(
        cards: DeckListCardEntity?,
        onClickDeleted:(Int) -> Unit
    ) {
        binding.AddCardDeckButton.text = "Delete Card"
        binding.tvCard.text = cards?.card_name
        Glide.with(binding.ivCard.context).load(cards?.image).into(binding.ivCard)
        binding.AddCardDeckButton.setOnClickListener { onClickDeleted(adapterPosition) }
    }
}