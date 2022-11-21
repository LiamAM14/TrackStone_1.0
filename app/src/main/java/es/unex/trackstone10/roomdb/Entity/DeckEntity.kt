package es.unex.trackstone10.roomdb.Entity

import android.content.Intent
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "deck_table")
class DeckEntity : Serializable {
    @ColumnInfo(name = "classid")
    var classid: Int? = 0

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String? = String()

    @ColumnInfo(name = "count")
    var count: Int? = 0

    @ColumnInfo(name = "user_id")
    var user_id: Int? = 0

    @Ignore
    internal constructor(classid: Int?, name: String, count: Int, user_id: Int) {
        this.classid = classid
        this.name = name
        this.count = count
        this.user_id = user_id
    }

    constructor(id: Int, classid: Int?, name: String, count: Int, user_id: Int) {
        this.id = id
        this.classid = classid
        this.name = name
        this.count = count
        this.user_id = user_id
    }

    @Ignore
    internal constructor(intent: Intent) {
        id = intent.getLongExtra(DECK_ID, 0).toInt()
        classid = intent.getLongExtra(CLASS_ID, 0).toInt()
        name = intent.getStringExtra(NAME_ID).toString()
        count = intent.getLongExtra(COUNT, 0).toInt()
        user_id = intent.getLongExtra(USER_ID, 0).toInt()
    }

    override fun toString(): String {
        return (id.toString() + classid.toString() + name + count.toString() + user_id.toString())
    }

    fun toLog(): String {
        return ("ID: " + id + ITEM_SEPARATOR + "Name: " + name)
    }

    companion object {
        @Ignore
        const val DECK_ID: String = "id"

        @Ignore
        const val NAME_ID: String = "name"

        @Ignore
        const val USER_ID: String = "userid"

        @Ignore
        const val CLASS_ID: String = "classid"

        @Ignore
        const val COUNT: String = "count"

        @Ignore
        val ITEM_SEPARATOR: String = System.getProperty("line.separator") as String
    }

}