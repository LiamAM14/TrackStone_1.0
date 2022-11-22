package es.unex.trackstone10.API

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

    @GET("/hearthstone/cards")
    suspend fun getCards(
        @Query("set") set: String,
        @Query("sort") byClass: String,
        @Query("pageSize") size: Int,
        @Query("locale") locale: String
    ): Response<CardResponseList>


    @GET("/hearthstone/cards")
    suspend fun getCardsByName(
        @Query("textFilter") name: String,
        @Query("set") set: String,
        @Query("sort") byClass: String,
        @Query("pageSize") size: Int,
        @Query("locale") locale: String
    ): Response<CardResponseList>

    @GET("/hearthstone/cards")
    suspend fun getCardsByClass(
        @Query("class") HSclass: String,
        @Query("set") set: String,
        @Query("sort") byMana: String,
        @Query("pageSize") size: Int,
        @Query("locale") locale: String
    ): Response<CardResponseList>

    @GET("/hearthstone/cards")
    suspend fun getCardsByClassAndName(
        @Query("textFilter") name:String,
        @Query("class") HSclass: String,
        @Query("set") set: String,
        @Query("sort") byMana: String,
        @Query("pageSize") size: Int,
        @Query("locale") locale: String
    ): Response<CardResponseList>

    @GET("/hearthstone/cardbacks")
    suspend fun getCardBacksByName(
        @Query("locale") locale: String
    ): Response<CardBackResponseList>

    @GET("/hearthstone/cardbacks")
    suspend fun getCardBacksByName(
        @Query("locale") locale: String,
        @Query("textFilter") name: String
    ): Response<CardBackResponseList>


    @GET("/hearthstone/cards")
    suspend fun getHeroByName(
        @Query("textFilter") name: String,
        @Query("set") set: String,
        @Query("sort") byClass: String,
        @Query("locale") locale: String
    ): Response<CardResponseList>

    @GET("/hearthstone/cards")
    suspend fun getHeroes(
        @Query("set") set: String,
        @Query("sort") byClass: String,
        @Query("locale") locale: String
    ): Response<CardResponseList>


}