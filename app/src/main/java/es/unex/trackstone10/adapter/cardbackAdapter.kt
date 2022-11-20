package es.unex.trackstone10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.API.CardBackResponse
import es.unex.trackstone10.R

class cardbackAdapter(val cardbacksList: List<CardBackResponse>, private val onClickListener: (CardBackResponse) -> Unit) : RecyclerView.Adapter<cardbackHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardbackHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return cardbackHolder(layoutInflater.inflate(R.layout.item_card, parent, false))
    }

    override fun onBindViewHolder(holder: cardbackHolder, position: Int) {
        val item = cardbacksList[position]
        holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int = cardbacksList.size
    }
