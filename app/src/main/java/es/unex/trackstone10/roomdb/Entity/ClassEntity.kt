package es.unex.trackstone10.roomdb.Entity

import android.content.Intent
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "class_table")
class ClassEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "id_hero")
    var idhero: String? = String()
    @ColumnInfo(name = "url")
    var url: String? = String()

    @Ignore
    internal constructor(idhero: String?,url: String?){
        this.idhero = idhero
        this.url = url
    }


    constructor(id: Int, idhero: String, url: String){
        this.id = id
        this.idhero = idhero
        this.url = url
    }

    @Ignore
    internal constructor(intent: Intent){
        id = intent.getLongExtra(CLASS_ID,0).toInt()
        idhero = intent.getStringExtra(CLASS_ID_HERO).toString()
        url = intent.getStringExtra(CLASS_URL).toString()
    }

    override fun toString(): String {
        return (id.toString() + ITEM_SEPARATOR + idhero + ITEM_SEPARATOR + url)
    }

    fun toLog(): String{
        return ("ID: " + id + ITEM_SEPARATOR + "ID_HERO: " + idhero + ITEM_SEPARATOR
                + "Url: " + url)
    }

    companion object{
        @Ignore
        const val CLASS_ID:String = "id"
        @Ignore
        const val CLASS_ID_HERO:String = "idhero"
        @Ignore
        const val CLASS_URL:String = "url"
        @Ignore
        val ITEM_SEPARATOR:String = System.getProperty("line.separator") as String

        fun packageIntent(intent: Intent,idhero: String?,url: String?){
            intent.putExtra(CLASS_ID_HERO,idhero)
            intent.putExtra(CLASS_URL,url)
        }
    }
}
