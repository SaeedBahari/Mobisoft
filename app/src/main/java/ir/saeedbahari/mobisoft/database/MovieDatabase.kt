package ir.saeedbahari.mobisoft.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem

@Database(
    entities =
    [SearchItem::class], version = 1, exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun depotDao(): MovieDao

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
                //.addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
