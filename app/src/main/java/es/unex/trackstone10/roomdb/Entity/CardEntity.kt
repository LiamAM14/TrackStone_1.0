package es.unex.trackstone10.roomdb.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CardEntity(
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "info") val info: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "race") val race: String
)