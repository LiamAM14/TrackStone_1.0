package es.unex.trackstone10.roomdb.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surnames") val surnames: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "mail") val mail: String,
    @ColumnInfo(name = "server") val server: String,
    @ColumnInfo(name = "age") val age: String,
    @ColumnInfo(name = "gender") val gender: String
)