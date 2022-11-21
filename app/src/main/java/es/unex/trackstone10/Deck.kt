package es.unex.trackstone10

import java.io.Serializable

data class Deck(
    val name: String,
    val dClass: ClassHS,
    val cardList: List<Cards>?
    ) : Serializable