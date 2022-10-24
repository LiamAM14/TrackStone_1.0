package es.unex.trackstone10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bBrowse =findViewById<Button>(R.id.browseButton)

        bBrowse.setOnClickListener(){ view ->
            intent = Intent(this, BrowseCardsActivity::class.java)
            startActivity(intent)
        }
    }
}