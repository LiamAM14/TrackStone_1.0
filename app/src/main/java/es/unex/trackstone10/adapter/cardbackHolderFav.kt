package es.unex.trackstone10.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.unex.trackstone10.databinding.ItemCardBinding
import es.unex.trackstone10.roomdb.Entity.CardBackEntity

class cardbackHolderFav(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCardBinding.bind(view)

    fun render(cardbacks: CardBackEntity?, onClickListener: (CardBackEntity) ->  Unit){
        if(cardbacks != null){
            Glide.with(binding.ivCard.context).load(cardbacks.url).into(binding.ivCard)
            itemView.setOnClickListener { onClickListener(cardbacks) }
        }
    }
}