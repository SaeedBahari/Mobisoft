package ir.saeedbahari.mobisoft.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem
import ir.saeedbahari.mobisoft.repository.datasource.SearchResultSource
import ir.saeedbahari.mobisoft.utils.MovieAppObj.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
class MoviesListRepository() {
    fun getSearchResultStream( apiKey: String, searchQuery: String): Flow<PagingData<SearchItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchResultSource(apiKey = apiKey,searchQuery =searchQuery) }
        ).flow
    }
}