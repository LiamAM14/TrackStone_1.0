package es.unex.trackstone10.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.unex.trackstone10.databinding.ItemCardBinding
import es.unex.trackstone10.roomdb.Entity.CardEntity

class cardHolderFav (view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCardBinding.bind(view)


    fun render(cards: CardEntity?, onClickListener: (CardEntity) -> Unit){
        if(cards != null) {
            binding.tvCard.text = cards.name
            when (cards.rarity) {
                1 -> binding.tvCard2.text = "Common"
                2 -> binding.tvCard2.text = "Free"
                3 -> binding.tvCard2.text = "Rare"
                4 -> binding.tvCard2.text = "Epic"
                5 -> binding.tvCard2.text = "Legendary"
            }
            Glide.with(binding.ivCard.context).load(cards.info).into(binding.ivCard)
            itemView.setOnClickListener { onClickListener(cards) }
        }
    }
}