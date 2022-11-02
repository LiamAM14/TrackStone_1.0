package es.unex.trackstone10

import java.io.Serializable

data class CardResponse (
    var id: Int? = null,
    var collectible: Int? = null,
    var slug: String? = null,
    var classId: Int? = null,
    var multiClassIds: List<Int>? = null,
    var spellSchoolId: Int? = null,
    var cardTypeId: Int? = null,
    var cardSetId: Int? = null,
    var rarityId: Int? = null,
    var artistName: String? = null,
    var manaCost: Int? = null,
    var name: String? = null,
    var text: String? = null,
    var image: String? = null,
    var imageGold: String? = null,
    var flavorText: String? = null,
    var cropImage: String? = null,
    var keywordIds: List<Int>? = null,
): Serializable