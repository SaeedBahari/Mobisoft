package ir.saeedbahari.mobisoft.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.saeedbahari.mobisoft.database.dataModels.RemoteKey
import ir.saeedbahari.mobisoft.database.dataModels.RemoteKeyDao
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem

@Database(
    entities =
    [SearchItem::class,
    RemoteKey::class], version = 1, exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    "movie_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
