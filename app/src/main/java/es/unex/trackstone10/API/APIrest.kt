package es.unex.trackstone10.API

import android.util.Base64
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIrest {
    private val client_id = "80a38acd60a44a37a3184b1940b7d251"
    private val client_secret = "XBLQx7o9BYS4JHMCo3U5Vtkx9TJBgmgw"
    var token: Token? = null
    var tokenExpire: Int? = null
    var cards : CardResponseList? = null

    fun getToken(): Int {
        var answer = 1
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
            token = call.body()

            if (call.isSuccessful) {
                if (token != null) {
                    tokenExpire = token?.expires_in
                    answer = 2
                }
            } else {
                answer = 0
            }
        }
        return answer
    }

    fun buildClientInterceptor(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor())
            .build()
    }

    fun getCards(query: String){
        CoroutineScope(Dispatchers.IO).launch{
            val client = buildClientInterceptor()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://us.api.blizzard.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                    .build()

            val call = retrofit.create(APIService::class.java).getCardsByName("en_US",query)

            cards = call.body()
        }
    }
}
