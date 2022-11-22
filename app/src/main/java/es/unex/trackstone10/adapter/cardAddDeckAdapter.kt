package es.unex.trackstone10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.R

class cardAddDeckAdapter(val cardsList: List<CardResponse>) : RecyclerView.Adapter<cardAddDeckHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardAddDeckHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return cardAddDeckHolder(layoutInflater.inflate(R.layout.item_add_card_deck, parent, false))
    }

    override fun onBindViewHolder(holder: cardAddDeckHolder, position: Int) {
        val item = cardsList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = cardsList.size



}