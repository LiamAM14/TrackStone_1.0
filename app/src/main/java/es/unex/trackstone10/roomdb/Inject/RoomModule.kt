package es.unex.trackstone10.roomdb.Inject

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.unex.trackstone10.roomdb.TrackstoneDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "database_name"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TrackstoneDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(db: TrackstoneDatabase) = db.getDao()
}