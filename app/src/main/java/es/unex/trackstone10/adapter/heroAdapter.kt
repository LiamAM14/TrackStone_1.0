package es.unex.trackstone10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.API.CardResponse
import es.unex.trackstone10.R

class heroAdapter(val heroList: List<CardResponse>, private val onClickListener: (CardResponse) -> Unit) : RecyclerView.Adapter<heroHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): heroHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return heroHolder(layoutInflater.inflate(R.layout.item_card,parent,false))
    }

    override fun onBindViewHolder(holder: heroHolder, position: Int) {
        val item = heroList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = heroList.size

    }