package es.unex.trackstone10.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.databinding.ItemCardBinding

class heroHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCardBinding.bind(view)

    fun render(cards:CardResponse,onClickListener: (CardResponse) -> Unit) {
        binding.tvCard.text = cards.name


        Glide.with(binding.ivCard.context).load(cards.image).into(binding.ivCard)
        itemView.setOnClickListener{ onClickListener(cards) }
    }
}