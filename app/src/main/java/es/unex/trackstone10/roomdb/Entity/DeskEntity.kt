package es.unex.trackstone10.roomdb.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "desk_table")
data class DeskEntity(
    @ColumnInfo(name = "class0") val class0: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "back_card") val back_card: String,
    @ColumnInfo(name = "hero") val hero: String,
    @ColumnInfo(name = "id_desk") val id_desk: String,
    @ColumnInfo(name = "id_card") val id_card: String,
    @ColumnInfo(name = "copies") val copies: String
)