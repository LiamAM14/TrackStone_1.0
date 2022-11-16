package es.unex.trackstone10.API

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val header = APIrest.token?.access_token
        val request =
            chain.request().newBuilder()
                .addHeader("Authorization:", "Bearer $header")
                .build()
        return chain.proceed(request)
    }
}