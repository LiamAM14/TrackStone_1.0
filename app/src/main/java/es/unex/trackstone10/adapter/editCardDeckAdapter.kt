package es.unex.trackstone10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.R
import es.unex.trackstone10.roomdb.Entity.DeckListCardEntity

class editCardDeckAdapter(
    val cardsList: List<DeckListCardEntity?>,
    private val onClickDeleted:(Int) -> Unit,
    var deckId:Int?,
    var conText: FragmentActivity) : RecyclerView.Adapter<editCardDeckHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): editCardDeckHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return editCardDeckHolder(layoutInflater.inflate(R.layout.item_add_card_deck, parent, false))
    }

    override fun onBindViewHolder(holder: editCardDeckHolder, position: Int) {
        val item = cardsList[position]
        holder.render(item, onClickDeleted)
    }

    override fun getItemCount(): Int = cardsList.size
}