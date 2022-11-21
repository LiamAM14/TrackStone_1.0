package es.unex.trackstone10.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.unex.trackstone10.roomdb.Entity.CardBackEntity
import es.unex.trackstone10.roomdb.Entity.CardEntity

@Dao
interface CardBackDao {

    @Query("SELECT * FROM card_back_table")
    fun getAll(): List<CardBackEntity?>?

    @Query("SELECT * FROM card_back_table WHERE name LIKE :nameQuery")
    fun getByName(nameQuery: String): List<CardBackEntity?>?

    @Insert
    fun insert(cardback : CardBackEntity?): Long

    @Query("DELETE FROM card_back_table")
    fun deleteAll()

    @Update
    fun update(cardback: CardBackEntity?): Int
}