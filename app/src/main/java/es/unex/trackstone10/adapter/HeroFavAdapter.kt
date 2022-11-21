package es.unex.trackstone10.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.unex.trackstone10.R
import es.unex.trackstone10.roomdb.Entity.ClassEntity

class HeroFavAdapter(val favHeroList: List<ClassEntity?>, private val onClickListener: (ClassEntity) -> Unit) : RecyclerView.Adapter<HeroFavHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroFavHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeroFavHolder(layoutInflater.inflate(R.layout.item_card,parent,false))
    }

    override fun onBindViewHolder(holder: HeroFavHolder, position: Int) {
        val item = favHeroList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = favHeroList.size
}