package es.unex.trackstone10.roomdb.Entity

import android.content.Intent
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "deck_list")
class DeckListCardEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "deck_id")
    var deck_id: Int? = 0
    @ColumnInfo(name = "user_id")
    var user_id: Int? = 0
    @ColumnInfo(name = "card_name")
    var card_name: String? = String()
    @ColumnInfo(name = "copies")
    var copies: Int? = 0
    @ColumnInfo(name = "card_rarity")
    var card_rarity: Int? = 0
    @ColumnInfo(name = "card_class")
    var card_class: Int? = 0
    @ColumnInfo(name = "card_manacost")
    var card_manacost: Int? = 0
    @ColumnInfo(name = "image")
    var image:String? = String()

    @Ignore
    internal constructor(deck_id: Int?,user_id: Int?,card_name: String?,copies: Int?,
    card_rarity:Int?,card_class:Int?,card_manacost: Int?, image: String?){
        this.deck_id = deck_id
        this.user_id = user_id
        this.card_name = card_name
        this.copies = copies
        this.card_rarity = card_rarity
        this.card_class = card_class
        this.card_manacost = card_manacost
        this.image = image
    }

    constructor(id: Int,deck_id: Int?,user_id: Int?,card_name: String?,copies: Int?,
                card_rarity:Int?,card_class:Int?,card_manacost: Int?, image: String?){
        this.id = id
        this.deck_id = deck_id
        this.user_id = user_id
        this.card_name = card_name
        this.copies = copies
        this.card_rarity = card_rarity
        this.card_class = card_class
        this.card_manacost = card_manacost
        this.image = image
    }

    @Ignore
    internal constructor(intent : Intent){
        id = intent.getLongExtra(DECK_LIST_ID,0).toInt()
        deck_id = intent.getIntExtra(DECK_ID,0)
        user_id = intent.getIntExtra(USER_ID,0)
        card_name = intent.getStringExtra(CARD_NAME).toString()
        copies = intent.getIntExtra(COPIES,0)
        card_rarity = intent.getIntExtra(CARD_RARITY,0)
        card_class = intent.getIntExtra(CARD_CLASS,0)
        card_manacost = intent.getIntExtra(CARD_MANACOST,0)
        image = intent.getStringExtra(IMAGE).toString()
    }

    override fun toString(): String {
        return (id.toString() + ITEM_SEPARATOR + deck_id.toString() + ITEM_SEPARATOR + user_id.toString() +
                ITEM_SEPARATOR + card_name + ITEM_SEPARATOR + copies.toString() + ITEM_SEPARATOR +
                card_rarity.toString() + ITEM_SEPARATOR + card_class.toString() + ITEM_SEPARATOR +
                card_manacost.toString() + ITEM_SEPARATOR + image)
    }

    fun toLog(): String{
        return ("ID: " + id + ITEM_SEPARATOR + "DECK_ID: " + deck_id + ITEM_SEPARATOR + "USER_ID: " + user_id + ITEM_SEPARATOR +
                "CARD_NAME: " + card_name + ITEM_SEPARATOR + "COPIES: " + copies + ITEM_SEPARATOR + "CARD_RARITY: " +
                card_rarity + ITEM_SEPARATOR + "CARD_CLASS: " + card_class + ITEM_SEPARATOR + "CARD_MANACOST: " +
                card_manacost + ITEM_SEPARATOR + "Image: " + image)
    }


    companion object{
        @Ignore
        const val DECK_LIST_ID:String = "decklistid"
        @Ignore
        const val DECK_ID:String = "id"
        @Ignore
        const val USER_ID:String = "deckid"
        @Ignore
        const val CARD_NAME:String = "name"
        @Ignore
        const val COPIES:String = "copies"
        @Ignore
        const val CARD_RARITY:String = "rarity"
        @Ignore
        const val CARD_CLASS:String = "cardclass"
        @Ignore
        const val CARD_MANACOST:String = "manacost"
        @Ignore
        const val IMAGE:String = "image"
        @Ignore
        val ITEM_SEPARATOR:String = System.getProperty("line.separator") as String

    }

}