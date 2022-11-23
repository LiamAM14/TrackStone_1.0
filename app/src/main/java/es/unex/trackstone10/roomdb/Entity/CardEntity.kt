package es.unex.trackstone10.roomdb.Entity

import android.content.Intent
import java.io.Serializable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
class CardEntity : Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String? = String()

    @ColumnInfo(name = "rarity")
    var rarity: Int? = 0

    @ColumnInfo(name = "cardclass")
    var cardclass: Int? = 0

    @ColumnInfo(name = "manacost")
    var manacost: Int? = 0

    @ColumnInfo(name = "info")
    var info: String? = String()

    @ColumnInfo(name = "type")
    var type: String? = String()

    @ColumnInfo(name = "race")
    var race: String? = String()

    @ColumnInfo(name = "userid")
    var userid: Int? = 0



    @Ignore
    internal constructor(name: String?,rarity:Int?,cardclass:Int?,manacost:Int?,info: String?,type: String?, race: String?,userid: Int?){
        this.name = name
        this.rarity = rarity
        this.cardclass = cardclass
        this.manacost = manacost
        this.info = info
        this.type = type
        this.race = race
        this.userid = userid
    }

    constructor(id: Int, name: String?,rarity:Int?,cardclass:Int?,manacost:Int?,info: String?,type: String?,race: String?,userid: Int?){
        this.id = id
        this.name = name
        this.rarity = rarity
        this.cardclass = cardclass
        this.manacost = manacost
        this.info = info
        this.type = type
        this.race = race
        this.userid = userid
    }


    @Ignore
    internal constructor(intent: Intent){
        id = intent.getLongExtra(CARD_ID,0).toInt()
        name = intent.getStringExtra(CARD_NAME).toString()
        rarity = intent.getIntExtra(CARD_RARITY,0)
        cardclass = intent.getIntExtra(CARD_CLASS,0)
        manacost = intent.getIntExtra(CARD_MANACOST,0)
        info = intent.getStringExtra(CARD_INFO).toString()
        type = intent.getStringExtra(CARD_TYPE).toString()
        race = intent.getStringExtra(CARD_RACE).toString()
        userid = intent.getIntExtra(USER_ID,0)
    }

    override fun toString(): String {
        return (id.toString() + ITEM_SEPARATOR + name + ITEM_SEPARATOR + rarity.toString() + ITEM_SEPARATOR + cardclass.toString() + ITEM_SEPARATOR
                + manacost.toString() + ITEM_SEPARATOR + info + ITEM_SEPARATOR + type + ITEM_SEPARATOR + race + ITEM_SEPARATOR + userid.toString())
    }

    fun toLog(): String {
        return ("ID: " + id + ITEM_SEPARATOR + "Name: " + name + ITEM_SEPARATOR + "Rarity: " + rarity + ITEM_SEPARATOR + "Card class: " + cardclass
        + ITEM_SEPARATOR+ "Manacost: " + manacost + ITEM_SEPARATOR +"Info: " + info + ITEM_SEPARATOR + "Type: " + type + ITEM_SEPARATOR + "Race: " + race +
                ITEM_SEPARATOR + "User_id: " + userid)
    }

    companion object{
        @Ignore
        const val CARD_ID:String = "id"
        @Ignore
        const val CARD_NAME:String = "name"
        @Ignore
        const val CARD_RARITY:String = "rarity"
        @Ignore
        const val CARD_CLASS:String = "cardclass"
        @Ignore
        const val CARD_MANACOST:String = "manacost"
        @Ignore
        const val CARD_INFO:String = "info"
        @Ignore
        const val CARD_TYPE:String = "type"
        @Ignore
        const val CARD_RACE:String = "race"
        @Ignore
        const val USER_ID:String = "userid"
        @Ignore
        val ITEM_SEPARATOR:String = System.getProperty("line.separator") as String

        fun packageIntent(intent: Intent,rarity: Int?,cardclass: Int?,manacost: Int?,name: String?,info: String?,type: String?,race: String?,userid: Int?){
            intent.putExtra(CARD_NAME,name)
            intent.putExtra(CARD_RARITY,rarity)
            intent.putExtra(CARD_CLASS,cardclass)
            intent.putExtra(CARD_MANACOST,manacost)
            intent.putExtra(CARD_INFO,info)
            intent.putExtra(CARD_TYPE,type)
            intent.putExtra(CARD_RACE,race)
            intent.putExtra(USER_ID,userid)
        }

    }
}