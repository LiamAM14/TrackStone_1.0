package es.unex.trackstone10.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.databinding.ItemCardBinding

class cardHolder(view: View) : ViewHolder(view) {

    val binding = ItemCardBinding.bind(view)


    fun render(cards: CardResponse, onClickListener: (CardResponse) -> Unit){
        binding.tvCard.text = cards.name
        when (cards.rarityId){
            1 -> binding.tvCard2.text = "40"
            2 -> binding.tvCard2.text = "Free"
            3 -> binding.tvCard2.text = "100"
            4 -> binding.tvCard2.text = "400"
            5 -> binding.tvCard2.text = "1600"
        }
        Glide.with(binding.ivCard.context).load(cards.image).into(binding.ivCard)
        itemView.setOnClickListener{ onClickListener(cards) }
    }
}