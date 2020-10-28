package ir.saeedbahari.mobisoft.database

import androidx.paging.PagingSource
import androidx.room.*
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem

@Dao
interface MovieDao {

    @Insert(entity = SearchItem::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieItems(item: List<SearchItem>)

    @Query("Select * from SearchItem ORDER BY id DESC")
    fun pagingSource():PagingSource<Int,SearchItem>

    @Query("DELETE FROM SearchItem")
    suspend fun clearAll()

    @Query("DELETE FROM SearchItem WHERE title Like :query")
    suspend fun deleteByQuery(query: String)

}