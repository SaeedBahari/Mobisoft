package ir.saeedbahari.mobisoft.repository.datasource

import androidx.paging.PagingSource
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem
import ir.saeedbahari.mobisoft.network.MovieListApi
import okio.IOException
import retrofit2.HttpException

class SearchResultSource(val apiKey: String, val searchQuery: String)
    : PagingSource<Int, SearchItem>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItem> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = MovieListApi.retrofitMovie.getMovieSearchList(
                apikey = apiKey,
                pageNumber = currentLoadingPageKey,
                searchField = searchQuery
            )
            val responseData = mutableListOf<SearchItem>()

            val data = response.body()?.search ?: emptyList()
            data.let {
                responseData.addAll(data.toMutableList())
            }
            val prevKey =  currentLoadingPageKey.minus(1)
            return LoadResult.Page(
                    data = responseData,
                    prevKey = null,
                    nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }
}