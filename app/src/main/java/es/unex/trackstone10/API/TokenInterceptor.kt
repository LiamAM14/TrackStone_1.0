package es.unex.trackstone10.API

import es.unex.trackstone10.ui.home.fragments.CardsFragment
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val header = APIToken.token?.access_token
        val bearer = "Bearer $header"
        val request =
            chain.request().newBuilder()
                .addHeader("Authorization", bearer)
                .build()
        return chain.proceed(request)
    }
}