package es.unex.trackstone10.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.unex.trackstone10.roomdb.Entity.DeckListCardEntity

@Dao
interface DeckListDao {

    @Query("SELECT * FROM deck_list")
    fun getAll(): List<DeckListCardEntity?>?

    @Insert
    fun insert(decklist : DeckListCardEntity?): Long

    @Query("DELETE FROM deck_list")
    fun deleteAll()

    @Update
    fun update(deckList : DeckListCardEntity?): Int
}