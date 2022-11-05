package es.unex.trackstone10.roomdb.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import es.unex.trackstone10.roomdb.Entity.*

@Dao
interface Dao {
    @Query("SELECT * FROM user_table")
    suspend fun getAllUser():List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(users:List<UserEntity>)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM desk_table")
    suspend fun getAllDesks():List<DeskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDesks(desks:List<DeskEntity>)

    @Query("DELETE FROM desk_table")
    suspend fun deleteAllDesks()

    @Query("SELECT * FROM favorite_desk_table")
    suspend fun getAllFavoriteDesks():List<FavoriteDeskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavoriteDesks(fdesks:List<FavoriteDeskEntity>)

    @Query("DELETE FROM favorite_desk_table")
    suspend fun deleteAllFavoriteDesks()

    @Query("SELECT * FROM favorite_card_table")
    suspend fun getAllFavoriteCards():List<FavoriteCardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavoriteCards(fcards:List<FavoriteCardEntity>)

    @Query("DELETE FROM favorite_card_table")
    suspend fun deleteAllFavoriteCards()

    @Query("SELECT * FROM favorite_class_table")
    suspend fun getAllFavoriteClasses():List<FavoriteClassEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavoriteClasses(fclasses:List<FavoriteClassEntity>)

    @Query("DELETE FROM favorite_class_table")
    suspend fun deleteAllFavoriteClasses()
}