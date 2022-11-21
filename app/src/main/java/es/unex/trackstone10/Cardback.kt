package es.unex.trackstone10

import java.io.Serializable

data class Cardback(
    val Id: Int,
    val sortCategory: Int,
    val text: String,
    val name: String,
    val image: String,
    val slug: String
) : Serializable