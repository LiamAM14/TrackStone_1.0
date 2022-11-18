package es.unex.trackstone10.roomdb.Entity

import android.content.Intent
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class UserEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "username")
    var username: String? = String()
    @ColumnInfo(name = "password")
    var password: String? = String()
    @ColumnInfo(name = "mail")
    var mail: String? = String()

    @Ignore
    internal constructor(username: String?,password: String?,mail: String?){
        this.username = username
        this.password = password
        this.mail = mail
    }


    constructor(id: Int,username: String?,password: String?,mail: String?){
        this.id = id
        this.username = username
        this.password = password
        this.mail = mail
    }

    @Ignore
    internal constructor (intent: Intent){
        id = intent.getLongExtra(USER_ID,0).toInt()
        username = intent.getStringExtra(USERNAME).toString()
        password = intent.getStringExtra(PASSWORD).toString()
        mail = intent.getStringExtra(MAIL).toString()
    }


    override fun toString() : String{
        return (id.toString() + ITEM_SEPARATOR + username + ITEM_SEPARATOR + password + ITEM_SEPARATOR + mail)
    }
    fun toLog(): String {
        return ("ID: " + id + ITEM_SEPARATOR + "Password: *********" + ITEM_SEPARATOR + "Username: " + username + ITEM_SEPARATOR + "Mail: " + mail)
    }


    companion object {
        @Ignore
        const val USER_ID:String = "id"
        @Ignore
        const val USERNAME:String = "username"
        @Ignore
        const val PASSWORD:String = "password"
        @Ignore
        const val MAIL:String = "mail"
        @Ignore
        val ITEM_SEPARATOR:String = System.getProperty("line.separator") as String

        fun packageIntent(intent: Intent, username: String?,password: String?,mail: String?){
            intent.putExtra(USERNAME,username)
            intent.putExtra(PASSWORD,password)
            intent.putExtra(MAIL,mail)
        }
    }
}