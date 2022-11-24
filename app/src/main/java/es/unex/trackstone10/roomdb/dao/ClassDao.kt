package es.unex.trackstone10.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.unex.trackstone10.roomdb.Entity.ClassEntity

@Dao
interface ClassDao {

    @Query("SELECT * FROM class_table WHERE userid = :userid")
    fun getAllById(userid: Int?): List<ClassEntity?>?

    @Query("SELECT * FROM class_table WHERE name LIKE :nameQuery AND userid = :userid")
    fun getByNameAndId(nameQuery: String,userid: Int?): List<ClassEntity?>?

    @Insert
    fun insert(classE: ClassEntity?): Long

    @Query("DELETE FROM class_table")
    fun deleteAll()

    @Update
    fun update(classE: ClassEntity?): Int

    @Query("DELETE FROM class_table WHERE id = :idClass")
    fun deleteFromId(idClass: Int)

    @Query("DELETE FROM class_table WHERE userid = :userid")
    fun deleteByUser(userid: Int?)
}