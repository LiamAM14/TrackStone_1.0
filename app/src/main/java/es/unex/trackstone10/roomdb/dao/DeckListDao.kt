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

    @Query("SELECT copies FROM deck_list WHERE deck_id = :deckID AND card_name LIKE :cardName")
    fun checkCopies(deckID: Int, cardName: String): Int


    @Query("SELECT * FROM deck_list WHERE deck_id = :deckID AND card_name LIKE :cardName")
    fun checkCard(deckID: Int, cardName: String): List<DeckListCardEntity>?

    @Query("UPDATE deck_list SET copies = copies+1 WHERE deck_id = :deckID AND card_name LIKE :cardName")
    fun incCopies(deckID: Int, cardName: String)

    @Update
    fun update(deckList : DeckListCardEntity?): Int

    @Query("DELETE FROM deck_list WHERE deck_id = :id")
    fun deleteByDeckId(id: Int?)
}