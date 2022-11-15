package es.unex.trackstone10.API

import java.io.Serializable

data class APICredentials(
    val Client_ID: String,
    val Client_Secret: String,
    val grant_type: String
    ) : Serializable
