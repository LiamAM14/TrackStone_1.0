package es.unex.trackstone10.roomdb.Entity

import android.content.Intent
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
class CardEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String? = String()

    @ColumnInfo(name = "info")
    var info: String? = String()

    @ColumnInfo(name = "type")
    var type: String? = String()

    @ColumnInfo(name = "race")
    var race: String? = String()


    @Ignore
    internal constructor(name: String?,info: String?,type: String?, race: String?){
        this.name = name
        this.info = info
        this.type = type
        this.race = race
    }

    constructor(id: Int, name: String?,info: String?,type: String?,race: String?){
        this.id = id
        this.name = name
        this.info = info
        this.type = type
        this.race = race
    }


    @Ignore
    internal constructor(intent: Intent){
        id = intent.getLongExtra(CARD_ID,0).toInt()
        name = intent.getStringExtra(CARD_NAME).toString()
        info = intent.getStringExtra(CARD_INFO).toString()
        type = intent.getStringExtra(CARD_TYPE).toString()
        race = intent.getStringExtra(CARD_RACE).toString()
    }

    override fun toString(): String {
        return (id.toString() + ITEM_SEPARATOR + name + ITEM_SEPARATOR + info + ITEM_SEPARATOR
                + type + ITEM_SEPARATOR + race + ITEM_SEPARATOR)
    }

    fun toLog(): String {
        return ("ID: " + id + ITEM_SEPARATOR + "Name: " + name + ITEM_SEPARATOR + "Info: " + info
                + ITEM_SEPARATOR + "Type: " + type + ITEM_SEPARATOR + "Race: " + race)
    }

    companion object{
        @Ignore
        const val CARD_ID:String = "id"
        @Ignore
        const val CARD_NAME:String = "name"
        @Ignore
        const val CARD_INFO:String = "info"
        @Ignore
        const val CARD_TYPE:String = "type"
        @Ignore
        const val CARD_RACE:String = "race"
        @Ignore
        val ITEM_SEPARATOR:String = System.getProperty("line.separator") as String

        fun packageIntent(intent: Intent,name: String?,info: String?,type: String?,race: String?){
            intent.putExtra(CARD_NAME,name)
            intent.putExtra(CARD_INFO,info)
            intent.putExtra(CARD_TYPE,type)
            intent.putExtra(CARD_RACE,race)
        }

    }
}