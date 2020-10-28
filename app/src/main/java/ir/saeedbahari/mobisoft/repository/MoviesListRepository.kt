package ir.saeedbahari.mobisoft.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.saeedbahari.mobisoft.database.MovieDatabase
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem
import ir.saeedbahari.mobisoft.repository.datasource.SearchResultSource
import ir.saeedbahari.mobisoft.utils.MovieAppObj.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
class MoviesListRepository(val context: Context) {

    val database = MovieDatabase.getDatabase(context)

    @OptIn(ExperimentalPagingApi::class)
    fun getSearchResultStream( apiKey: String, searchQuery: String): Flow<PagingData<SearchItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
               remoteMediator = SearchResultSource(apiKey = apiKey,searchQuery =searchQuery,database = database) ,
            pagingSourceFactory = { database.movieDao().pagingSource() }
        ).flow
    }
}