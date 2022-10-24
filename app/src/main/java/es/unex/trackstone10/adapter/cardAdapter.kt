package es.unex.trackstone10.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.Cards

class cardAdapter(val cardsList: List<Cards>) : RecyclerView.Adapter<cardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardHolder {
    }

    override fun onBindViewHolder(holder: cardHolder, position: Int) {
    }

    override fun getItemCount(): Int = cardsList.size

}