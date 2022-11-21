package es.unex.trackstone10.roomdb.Entity

import android.content.Intent
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "card_back_table")
class CardBackEntity : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "name")
    var name:String? = String()
    @ColumnInfo(name = "url")
    var url: String? = String()

    @Ignore
    internal constructor(name:String?,url: String?){
        this.name = name
        this.url = url
    }

    constructor(id: Int,name: String?, url: String){
        this.id = id
        this.name = name
        this.url = url
    }

    @Ignore
    internal constructor(intent: Intent){
        id = intent.getLongExtra(CARDBACK_ID,0).toInt()
        name = intent.getStringExtra(CARDBACK_NAME).toString()
        url = intent.getStringExtra(CARDBACK_URL).toString()
    }

    override fun toString(): String {
        return (id.toString() + ITEM_SEPARATOR + name + ITEM_SEPARATOR + url)
    }

    fun toLog(): String{
        return ("ID: " + id + ITEM_SEPARATOR + "Name: " + name + ITEM_SEPARATOR + "Url: " + url)
    }


    companion object{
        @Ignore
        const val CARDBACK_ID:String = "id"
        @Ignore
        const val CARDBACK_NAME:String = "name"
        @Ignore
        const val CARDBACK_URL:String = "url"
        @Ignore
        val ITEM_SEPARATOR:String = System.getProperty("line.separator") as String

        fun packageIntent(intent: Intent,name: String?,url: String?){
            intent.putExtra(CARDBACK_NAME,name)
            intent.putExtra(CARDBACK_URL,url)
        }
    }
}