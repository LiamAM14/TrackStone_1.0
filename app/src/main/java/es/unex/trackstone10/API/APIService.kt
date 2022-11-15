package es.unex.trackstone10.API

import es.unex.trackstone10.CardResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @FormUrlEncoded
    @POST("/token")
    fun getTokenCall(
        @Header("Authorization") authorization: String,
        @Field("grant_type") grant_type: String
    ): Call<Token>


    @GET()
    fun getCardsByName(@Url url: String): Response<CardResponse>
}