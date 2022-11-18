package es.unex.trackstone10.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.unex.trackstone10.roomdb.Entity.CardEntity

@Dao
interface CardDao {

    @Query("SELECT * FROM card_table")
    fun getAll(): List<CardEntity?>?

    @Insert
    fun insert(card : CardEntity?): Long

    @Query("DELETE FROM card_table")
    fun deleteAll()

    @Update
    fun update(card : CardEntity?): Int
}