package es.unex.trackstone10.roomdb.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "class_table")
data class ClassEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "id_hero") val id_hero: String,
    @ColumnInfo(name = "url") val url: String
)
