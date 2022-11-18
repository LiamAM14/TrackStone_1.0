package es.unex.trackstone10.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.unex.trackstone10.roomdb.Entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getAll(): List<UserEntity?>?

    @Insert
    fun insert(user : UserEntity?): Long

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Update
    fun update(user: UserEntity?): Int
}