package es.unex.trackstone10.roomdb.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "favorite_card_table")
data class FavoriteCardEntity(
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "info") val info: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "race") val race: String
)