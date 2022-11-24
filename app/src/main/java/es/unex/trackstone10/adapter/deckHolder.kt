package es.unex.trackstone10.adapter

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.AppExecutors
import es.unex.trackstone10.CardFavInfoActivity
import es.unex.trackstone10.EditDeckActivity
import es.unex.trackstone10.R
import es.unex.trackstone10.databinding.ItemDeckBinding
import es.unex.trackstone10.roomdb.Entity.DeckEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase


class deckHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemDeckBinding.bind(view)

   fun render(deck: DeckEntity?, context: FragmentActivity?) {
       if (deck != null) {
           binding.deckName.text = deck.name
           when (deck.classid) {
               1 -> binding.imageViewClassDeck.setImageResource(R.drawable.deathknight)
               2 -> binding.imageViewClassDeck.setImageResource(R.drawable.demonhunter)
               3 -> binding.imageViewClassDeck.setImageResource(R.drawable.druid)
               4 -> binding.imageViewClassDeck.setImageResource(R.drawable.hunter)
               5 -> binding.imageViewClassDeck.setImageResource(R.drawable.mage)
               6 -> binding.imageViewClassDeck.setImageResource(R.drawable.paladin)
               7 -> binding.imageViewClassDeck.setImageResource(R.drawable.priest)
               8 -> binding.imageViewClassDeck.setImageResource(R.drawable.rogue)
               9 -> binding.imageViewClassDeck.setImageResource(R.drawable.shaman)
               10 -> binding.imageViewClassDeck.setImageResource(R.drawable.warlock)
               11 -> binding.imageViewClassDeck.setImageResource(R.drawable.warrior)

           }
       }
       binding.editDeckButton.setOnClickListener{
           val intent:Intent = Intent(context, EditDeckActivity::class.java)
           intent.putExtra("DECK_ID",deck?.id)
           context?.startActivity(intent)
       }
       binding.deleteDeckButton.setOnClickListener {
           AppExecutors.instance?.diskIO()?.execute {
               val db = TrackstoneDatabase.getInstance(context)
               db?.deckDao?.deleteDeckFromId(deck?.id)
               db?.deckListDao?.deleteByDeckId(deck?.id)
           }
       }
   }

}