package es.unex.trackstone10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.Deck
import es.unex.trackstone10.R
import es.unex.trackstone10.roomdb.Entity.CardBackEntity
import es.unex.trackstone10.roomdb.Entity.DeckEntity

class deckAdapter(private val deckList: List<DeckEntity?>): RecyclerView.Adapter<deckHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): deckHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return deckHolder(layoutInflater.inflate(R.layout.item_deck, parent, false))
    }

    override fun onBindViewHolder(holder: deckHolder, position: Int) {
        val item = deckList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = deckList.size

}