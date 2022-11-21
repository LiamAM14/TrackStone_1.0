package es.unex.trackstone10.ui.decks

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import es.unex.trackstone10.CreateDeckActivity
import es.unex.trackstone10.Deck
import es.unex.trackstone10.R
import es.unex.trackstone10.RegisterActivity
import es.unex.trackstone10.databinding.FragmentDecksBinding


class DecksFragment : Fragment() {

    private lateinit var binding: FragmentDecksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDecksBinding.inflate(inflater,container,false)
        val view = binding.root
        initRecyclerView()
        binding.buttonCreateDeck.setOnClickListener{
            val intent = Intent(activity,CreateDeckActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    private fun initRecyclerView(){
    }


}