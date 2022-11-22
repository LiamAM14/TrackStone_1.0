package es.unex.trackstone10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainMenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bBrowse = findViewById<Button>(R.id.browseButton)
        val bDecks = findViewById<Button>(R.id.decksButton)
        val bFavs = findViewById<Button>(R.id.favButton)
        val bHero = findViewById<Button>(R.id.heroButton)
        val bProfile = findViewById<Button>(R.id.profileButton)
        val bCardback = findViewById<Button>(R.id.cardbacksButton)

        bBrowse.setOnClickListener() { view ->
            intent = Intent(this, BrowseCardsActivity::class.java)
            startActivity(intent)
        }

        bDecks.setOnClickListener() { view ->
            intent = Intent(this, BrowseDecksActivity::class.java)
            startActivity(intent)
        }

        bFavs.setOnClickListener() { view ->
//            intent = Intent(this, )
//            startActivity(intent)
        }

        bHero.setOnClickListener() { view ->
            intent = Intent(this, HeroInfoActivity::class.java)
            startActivity(intent)
        }

        bCardback.setOnClickListener() { view ->
            intent = Intent(this, CardBackInfoActivity::class.java)
            startActivity(intent)
        }
    }
}

