package es.unex.trackstone10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.R
import es.unex.trackstone10.roomdb.Entity.CardEntity

class cardAdapterFav(val cardList: List<CardEntity?>, private val onClickListener: (CardEntity) -> Unit) : RecyclerView.Adapter<cardHolderFav>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardHolderFav {
        val layoutInflater = LayoutInflater.from(parent.context)
        return cardHolderFav(layoutInflater.inflate(R.layout.item_card,parent,false))
    }

    override fun onBindViewHolder(holder: cardHolderFav, position: Int) {
        val item = cardList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = cardList.size
    }