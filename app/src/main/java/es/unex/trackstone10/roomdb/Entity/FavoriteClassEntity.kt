package es.unex.trackstone10.roomdb.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "favorite_class_table")
data class FavoriteClassEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "id_hero") val id_hero: String,
    @ColumnInfo(name = "url") val url: String
)
