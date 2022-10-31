package es.unex.trackstone10

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    fun getCardsByName(@Url url: String): Response<CardResponse>
}