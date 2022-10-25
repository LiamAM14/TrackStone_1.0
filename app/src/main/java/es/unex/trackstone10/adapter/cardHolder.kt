package es.unex.trackstone10.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import es.unex.trackstone10.Cards
import es.unex.trackstone10.R

class cardHolder(view: View) : ViewHolder(view) {

    val card = view.findViewById<TextView>(R.id.tvCard)
    val cardCost = view.findViewById<TextView>(R.id.tvCard2)
    val image = view.findViewById<ImageView>(R.id.ivCard)
    fun render(cards: Cards){
        card.text = cards.Name
        when (cards.RarityId){
            1 -> cardCost.text = "40"
            2 -> cardCost.text = "Free"
            3 -> cardCost.text = "100"
            4 -> cardCost.text = "400"
            5 -> cardCost.text = "1600"
        }
        Glide.with(image.context).load(cards.Image).into(image)
    }
}