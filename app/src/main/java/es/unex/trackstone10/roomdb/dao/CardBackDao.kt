package es.unex.trackstone10.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.unex.trackstone10.roomdb.Entity.CardBackEntity

@Dao
interface CardBackDao {

    @Query("SELECT * FROM card_back_table")
    fun getAll(): List<CardBackEntity?>?

    @Insert
    fun insert(cardback : CardBackEntity?): Long

    @Query("DELETE FROM card_back_table")
    fun deleteAll()

    @Update
    fun update(cardback: CardBackEntity?): Int
}