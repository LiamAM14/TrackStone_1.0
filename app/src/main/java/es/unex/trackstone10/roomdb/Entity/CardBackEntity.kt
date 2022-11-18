package es.unex.trackstone10.roomdb.Entity

import android.content.Intent
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "card_back_table")
class CardBackEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "url")
    var url: String? = String()

    @Ignore
    internal constructor(url: String?){
        this.url = url
    }

    constructor(id: Int, url: Int){
        this.id = id
        this.url = url
    }

    @Ignore
    internal constructor(intent: Intent){
        id = intent.getLongExtra(CARDBACK_ID,0).toInt()
        url = intent.getStringExtra(CARDBACK_URL).toString()
    }

    override fun toString(): String {
        return (id.toString() + ITEM_SEPARATOR + url)
    }

    fun toLog(): String{
        return ("ID: " + id + ITEM_SEPARATOR + "Url: " + url)
    }


    companion object{
        @Ignore
        const val CARDBACK_ID:String = "id"
        @Ignore
        const val CARDBACK_URL:String = "url"
        @Ignore
        val ITEM_SEPARATOR:String = System.getProperty("line.separator") as String

        fun packageIntent(intent: Intent,url: String?){
            intent.putExtra(CARDBACK_URL,url)
        }
    }
}