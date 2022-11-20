package es.unex.trackstone10.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.unex.trackstone10.roomdb.Entity.*
import es.unex.trackstone10.roomdb.dao.*

@Database(entities = [UserEntity::class,CardBackEntity::class,ClassEntity::class,CardEntity::class,DeskEntity::class], version = 2, exportSchema = false)
abstract class TrackstoneDatabase: RoomDatabase() {

    abstract val userdao: UserDao?
    abstract val carddao: CardDao?


    companion object{
        private var instance: TrackstoneDatabase? = null
        fun getInstance(context: Context?): TrackstoneDatabase? {
            if(instance == null){
                if(context != null){
                    instance = Room.databaseBuilder(
                        context,
                        TrackstoneDatabase::class.java,"trackstone.db"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return instance
        }
    }
}