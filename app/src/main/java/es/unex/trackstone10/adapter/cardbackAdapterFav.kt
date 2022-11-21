package es.unex.trackstone10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.R
import es.unex.trackstone10.roomdb.Entity.CardBackEntity

class cardbackAdapterFav(
    val cardBackList: List<CardBackEntity?>,
    private val onClickListener: (CardBackEntity) -> Unit
) : RecyclerView.Adapter<cardbackHolderFav>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardbackHolderFav {
        val layoutInflater = LayoutInflater.from(parent.context)
        return cardbackHolderFav(layoutInflater.inflate(R.layout.item_card, parent, false))
    }

    override fun onBindViewHolder(holder: cardbackHolderFav, position: Int) {
        val item = cardBackList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = cardBackList.size
}