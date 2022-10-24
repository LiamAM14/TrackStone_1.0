package es.unex.trackstone10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BrowseCardsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CardProvider.cardsList
        setContentView(R.layout.activity_browse_cards)
    }
}