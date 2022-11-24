package es.unex.trackstone10.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.unex.trackstone10.roomdb.Entity.CardEntity

@Dao
interface CardDao {

    @Query("SELECT * FROM card_table WHERE userid = :userid ORDER BY cardclass,manacost")
    fun getAllById(userid: Int?): List<CardEntity?>?

    @Query("SELECT * FROM card_table WHERE name LIKE :nameQuery AND userid = :userid")
    fun getByNameAndId(nameQuery:String,userid: Int?): List<CardEntity?>?

    @Insert
    fun insert(card : CardEntity?): Long

    @Query("DELETE FROM card_table")
    fun deleteAll()

    @Update
    fun update(card : CardEntity?): Int

    @Query("DELETE FROM card_table WHERE name LIKE :nameQuery")
    fun deleteByName(nameQuery: String?)

    @Query("DELETE FROM card_table WHERE userid = :userid")
    fun deleteByUser(userid: Int?)

}