package es.unex.trackstone10

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.unex.trackstone10.API.APIService
import es.unex.trackstone10.API.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainMenuActivity : AppCompatActivity() {

    private val client_id = "80a38acd60a44a37a3184b1940b7d251"
    private val client_secret = "XBLQx7o9BYS4JHMCo3U5Vtkx9TJBgmgw"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getToken()

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
            intent = Intent(this, Heroe_skinInfoActivity::class.java)
            startActivity(intent)
        }

        bProfile.setOnClickListener() { view ->
            intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        bCardback.setOnClickListener() { view ->
            intent = Intent(this, Card_backInfoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getToken() {
        val credentials = "$client_id:$client_secret"
        val credentialsData = credentials.toByteArray()
        val credentialsBase64 = Base64.encodeToString(credentialsData, Base64.NO_WRAP)
        val header = "Basic $credentialsBase64".trim()

        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder().baseUrl("https://oauth.battle.net/token/")
                .addConverterFactory(GsonConverterFactory.create()).client(client)
                .build()
            val call =
                retrofit.create(APIService::class.java).getTokenCall(header, "client_credentials")
                    .execute()
            val token: Token? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    if (token != null) {

                    }
                } else {
                    showError()
                }
            }
        }
    }

    fun showError() =
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()

}