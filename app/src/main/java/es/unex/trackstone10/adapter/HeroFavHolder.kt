package es.unex.trackstone10.adapter

import android.content.DialogInterface.OnClickListener
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.unex.trackstone10.databinding.ItemCardBinding
import es.unex.trackstone10.roomdb.Entity.ClassEntity

class HeroFavHolder (view: View): RecyclerView.ViewHolder(view){

    val binding = ItemCardBinding.bind(view)

    fun render(cards: ClassEntity, onClickListener: (ClassEntity) -> Unit){
//        binding.tvCard.text = cards.name
        Glide.with(binding.ivCard.context).load(cards.url).into(binding.ivCard)
    }
}