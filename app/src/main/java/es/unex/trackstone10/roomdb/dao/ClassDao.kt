package es.unex.trackstone10.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.unex.trackstone10.roomdb.Entity.ClassEntity

@Dao
interface ClassDao {

    @Query("SELECT * FROM class_table")
    fun getAll(): List<ClassEntity?>?

    @Insert
    fun insert(classE: ClassEntity?): Long

    @Query("DELETE FROM class_table")
    fun deleteAll()

    @Update
    fun update(classE: ClassEntity?): Int
}