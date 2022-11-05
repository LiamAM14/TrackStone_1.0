package es.unex.trackstone10.roomdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import es.unex.trackstone10.roomdb.Entity.*
import es.unex.trackstone10.roomdb.dao.*

@Database(entities = [UserEntity::class,FavoriteDeskEntity::class,FavoriteClassEntity::class,FavoriteCardEntity::class,DeskEntity::class], version = 1)
abstract class TrackstoneDatabase:RoomDatabase() {

    abstract fun getDao():Dao
}