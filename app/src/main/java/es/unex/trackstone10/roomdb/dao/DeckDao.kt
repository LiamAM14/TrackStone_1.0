package es.unex.trackstone10.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.unex.trackstone10.roomdb.Entity.DeckEntity

@Dao
interface DeckDao {

    @Query("SELECT * FROM deck_table")
    fun getAll(): List<DeckEntity?>?

    @Query("SELECT * FROM deck_table WHERE user_id = :id")
    fun getAllFromUser(id: String): List<DeckEntity?>?

    @Insert
    fun insert(deck : DeckEntity?): Long

    @Query("DELETE FROM deck_table")
    fun deleteAll()
}