package es.unex.trackstone10

import java.io.Serializable

data class ClassHS(
    val slug: String,
    val id: Int,
    val name: String,
    val cardId: Int,
    val heroPowerCardId: Int,
    val alteranteHeroCardsIds: Array<Int>,
    val image: Int
): Serializable