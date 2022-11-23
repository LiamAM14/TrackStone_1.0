package es.unex.trackstone10.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import es.unex.trackstone10.API.CardBackResponse
import es.unex.trackstone10.databinding.ItemCardBinding

class cardbackHolder(view: View) : ViewHolder(view){

    val binding = ItemCardBinding.bind(view)

    fun render(cardbacks: CardBackResponse, onClickListener: (CardBackResponse) -> Unit){
        binding.tvCard.text = cardbacks.name

        binding.tvCard2.text = ""
        Glide.with(binding.ivCard.context).load(cardbacks.image).into(binding.ivCard)
        itemView.setOnClickListener{ onClickListener(cardbacks) }
    }

}