package es.unex.trackstone10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.R
import es.unex.trackstone10.roomdb.Entity.DeckEntity

class deckAdapter(val deckList: List<DeckEntity?>,var conText: FragmentActivity?, private val onClickDeleted:(Int) -> Unit): RecyclerView.Adapter<deckHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): deckHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return deckHolder(layoutInflater.inflate(R.layout.item_deck, parent, false))
    }

    override fun onBindViewHolder(holder: deckHolder, position: Int) {
        val item = deckList[position]
        holder.render(item,conText, onClickDeleted)
    }

    override fun getItemCount(): Int = deckList.size

}