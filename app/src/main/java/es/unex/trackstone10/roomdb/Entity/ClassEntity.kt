package es.unex.trackstone10.roomdb.Entity

import android.content.Intent
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "class_table")
class ClassEntity : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "name")
    var name: String? = String()
    @ColumnInfo(name = "id_hero")
    var idhero: Int? = 0
    @ColumnInfo(name = "url")
    var url: String? = String()

    @Ignore
    internal constructor(name: String?,idhero: Int?,url: String?){
        this.name = name
        this.idhero = idhero
        this.url = url
    }


    constructor(id: Int, name: String?,idhero: Int?, url: String){
        this.name = name
        this.id = id
        this.idhero = idhero
        this.url = url
    }

    @Ignore
    internal constructor(intent: Intent){
        id = intent.getLongExtra(CLASS_ID,0).toInt()
        name = intent.getStringExtra(CLASS_NAME).toString()
        idhero = intent.getIntExtra(CLASS_ID_HERO,0)
        url = intent.getStringExtra(CLASS_URL).toString()
    }

    override fun toString(): String {
        return (id.toString() + ITEM_SEPARATOR + name + ITEM_SEPARATOR + idhero + ITEM_SEPARATOR + url)
    }

    fun toLog(): String{
        return ("ID: " + id + ITEM_SEPARATOR + "Name: " + name + ITEM_SEPARATOR + "ID_HERO: " + idhero + ITEM_SEPARATOR
                + "Url: " + url)
    }

    companion object{
        @Ignore
        const val CLASS_ID:String = "id"
        @Ignore
        const val CLASS_NAME:String = "name"
        @Ignore
        const val CLASS_ID_HERO:String = "idhero"
        @Ignore
        const val CLASS_URL:String = "url"
        @Ignore
        val ITEM_SEPARATOR:String = System.getProperty("line.separator") as String

        fun packageIntent(intent: Intent,name: String?,idhero: Int?,url: String?){
            intent.putExtra(CLASS_ID_HERO,idhero)
            intent.putExtra(CLASS_NAME,name)
            intent.putExtra(CLASS_URL,url)
        }
    }
}
