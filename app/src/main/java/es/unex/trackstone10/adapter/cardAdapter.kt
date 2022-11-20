package es.unex.trackstone10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.R

class cardAdapter(val cardsList: List<CardResponse>, private val onClickListener: (CardResponse) -> Unit) : RecyclerView.Adapter<cardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return cardHolder(layoutInflater.inflate(R.layout.item_card, parent, false))
    }

    override fun onBindViewHolder(holder: cardHolder, position: Int) {
        val item = cardsList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = cardsList.size

}