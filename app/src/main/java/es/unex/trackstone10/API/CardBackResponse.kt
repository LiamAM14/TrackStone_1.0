package es.unex.trackstone10.API

import java.io.Serializable

data class CardBackResponse (
    var id: Int? = null,
    var sortCategory: Int? = null,
    var text: String? = null,
    var name: String? = null,
    var image: String? = null,
    var slug: String? = null,
): Serializable
