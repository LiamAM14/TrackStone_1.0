package es.unex.trackstone10.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.unex.trackstone10.roomdb.Entity.DeckEntity

@Dao
interface DeckDao {

    @Query("SELECT * FROM deck_table")
    fun getAll(): List<DeckEntity?>?

    @Query("SELECT * FROM deck_table WHERE name = :name")
    fun getEntity(name: String): DeckEntity?

    @Query("SELECT count FROM deck_table WHERE id = :idDeck")
    fun getCountCards(idDeck:Int?): Int?

    @Query("UPDATE deck_table SET count = count + 1 WHERE id = :idDeck")
    fun AddingCards(idDeck:Int?)

    @Query("SELECT * FROM deck_table WHERE user_id = :id")
    fun getAllFromUser(id: String): List<DeckEntity?>?

    @Insert
    fun insert(deck : DeckEntity?): Long

    @Query("DELETE FROM deck_table")
    fun deleteAll()

    @Query("DELETE FROM deck_table WHERE id = :id")
    fun deleteDeckFromId(id: Int?)

    @Query("UPDATE deck_table SET count = count-1 WHERE id = :deckID")
    fun decCount(deckID: Int)

    @Query("SELECT classid FROM deck_table WHERE id = :deckID AND user_id = :userID")
    fun getSlug(deckID: Int, userID:Int): Int?

    @Query("DELETE FROM deck_table WHERE user_id = :userid")
    fun deleteByUser(userid: Int?)

}