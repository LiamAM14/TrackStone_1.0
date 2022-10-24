package es.unex.trackstone10

data class Cards (
    val Id : Int,
    val slug : String,
    val multiClassId : Array<Int>?,
    val Name : String,
    val TypeId : Int,
    val manaCost : Int,
    val attack : Int,
    val health : Int,
    val RarityId : Int,
    val cardSetId : Int,
    val Image : String,
    val ClassId : Int,
    val text : String,
    val cropImage : String,
    val keywords : Array<Int>
    )
