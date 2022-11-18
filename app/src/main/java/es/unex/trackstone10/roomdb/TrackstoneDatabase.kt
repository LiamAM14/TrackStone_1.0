package es.unex.trackstone10.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.unex.trackstone10.roomdb.Entity.*
import es.unex.trackstone10.roomdb.dao.*

@Database(entities = [UserEntity::class,CardBackEntity::class,ClassEntity::class,CardEntity::class,DeskEntity::class], version = 1)
abstract class TrackstoneDatabase: RoomDatabase() {

    abstract val user_dao: UserDao?


    companion object{
        private var instance: TrackstoneDatabase? = null
        fun getInstance(context: Context?): TrackstoneDatabase? {
            if(instance == null){
                if(context != null){
                    instance = Room.databaseBuilder(
                        context,
                        TrackstoneDatabase::class.java,"trackstone.db"
                    ).build()
                }
            }
            return instance
        }
    }
}