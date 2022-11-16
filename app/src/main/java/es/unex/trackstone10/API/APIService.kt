package es.unex.trackstone10.API

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @FormUrlEncoded
    @POST("/token")
    suspend fun getTokenCall(
        @Header("Authorization") authorization: String,
        @Field("grant_type") grant_type: String
    ): Call<Token>


    @GET("/hearthstone/cards")
    suspend fun getCardsByName(@Url url: String): Response<CardResponseList>


}