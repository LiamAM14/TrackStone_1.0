package es.unex.trackstone10.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.unex.trackstone10.roomdb.Entity.CardBackEntity

@Dao
interface CardBackDao {

    @Query("SELECT * FROM card_back_table WHERE userid = :userid")
    fun getAllById(userid : Int?): List<CardBackEntity?>?

    @Query("SELECT * FROM card_back_table WHERE name LIKE :nameQuery AND userid = :userid")
    fun getByNameAndId(nameQuery: String,userid : Int?): List<CardBackEntity?>?

    @Insert
    fun insert(cardback : CardBackEntity?): Long

    @Query("DELETE FROM card_back_table")
    fun deleteAll()

    @Update
    fun update(cardback: CardBackEntity?): Int

    @Query("DELETE FROM card_back_table WHERE id = :idCardBack")
    fun deleteFromId(idCardBack: Int)

    @Query("DELETE FROM card_back_table WHERE userid = :userid")
    fun deleteByUser(userid: Int?)
}