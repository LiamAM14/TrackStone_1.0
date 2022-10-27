package es.unex.trackstone10.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import es.unex.trackstone10.Cards
import es.unex.trackstone10.databinding.ItemCardBinding

class cardHolder(view: View) : ViewHolder(view) {

    val binding = ItemCardBinding.bind(view)


    fun render(cards: Cards, onClickListener: (Cards) -> Unit){
        binding.tvCard.text = cards.Name
        when (cards.RarityId){
            1 -> binding.tvCard2.text = "40"
            2 -> binding.tvCard2.text = "Free"
            3 -> binding.tvCard2.text = "100"
            4 -> binding.tvCard2.text = "400"
            5 -> binding.tvCard2.text = "1600"
        }
        Glide.with(binding.ivCard.context).load(cards.Image).into(binding.ivCard)
        itemView.setOnClickListener{ onClickListener(cards) }
    }
}