package ir.saeedbahari.mobisoft.database.dataModels

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeyDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOrReplace(remoteKey: RemoteKey)

  @Query("SELECT * FROM remote_keys WHERE label = :query ORDER BY id DESC ")
  suspend fun remoteKeyByQuery(query: String): List<RemoteKey>

  @Query("DELETE FROM remote_keys WHERE label = :query")
  suspend fun deleteByQuery(query: String)
}