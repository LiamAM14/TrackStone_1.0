package es.unex.trackstone10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.R

class cardAddDeckAdapter(val cardsList: List<CardResponse>,var id:Int?, var user:Int?,var conText: FragmentActivity?) : RecyclerView.Adapter<cardAddDeckHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardAddDeckHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return cardAddDeckHolder(layoutInflater.inflate(R.layout.item_add_card_deck, parent, false))
    }

    override fun onBindViewHolder(holder: cardAddDeckHolder, position: Int) {
        val item = cardsList[position]
        holder.render(item,id,user, conText)
    }

    override fun getItemCount(): Int = cardsList.size



}