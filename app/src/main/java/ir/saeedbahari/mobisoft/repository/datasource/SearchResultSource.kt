package ir.saeedbahari.mobisoft.repository.datasource

import androidx.paging.*
import androidx.room.withTransaction
import ir.saeedbahari.amoozeshgah.utils.networkMonitoring.NetworkStateHolder
import ir.saeedbahari.mobisoft.database.MovieDatabase
import ir.saeedbahari.mobisoft.database.dataModels.RemoteKey
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem
import ir.saeedbahari.mobisoft.network.MovieListApi
import ir.saeedbahari.mobisoft.network.apiModels.ResponseSearch
import ir.saeedbahari.mobisoft.utils.MovieAppObj
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

@OptIn(ExperimentalPagingApi::class)
class SearchResultSource(
    val apiKey: String,
    val searchQuery: String,
    val database: MovieDatabase
) : RemoteMediator<Int, SearchItem>() {
    val movieDao = database.movieDao()
    val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SearchItem>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull() ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                    getMovies()
                }
            }

            val response = MovieListApi.retrofitMovie.getMovieSearchList(
                apikey = apiKey,
                pageNumber = (if (loadKey != null) loadKey.nextKey else 1) as Int,
                searchField = searchQuery
            )
            val next = if (loadKey != null) loadKey.nextKey!!.toInt().plus(1) else 1
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByQuery(searchQuery)
                    remoteKeyDao.insertOrReplace(RemoteKey(label = searchQuery, nextKey = next))
                    movieDao.deleteByQuery(searchQuery)
                } else if (loadType == LoadType.APPEND)
                    remoteKeyDao.insertOrReplace(RemoteKey(label = searchQuery, nextKey = next))
                response.body()?.let {
                    movieDao.insertMovieItems(it.search!!)
                }
            }
            MediatorResult.Success(
                endOfPaginationReached = response.body()!!.search!!.size.rem(MovieAppObj.NETWORK_PAGE_SIZE) < MovieAppObj.NETWORK_PAGE_SIZE
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }

    private suspend fun getMovies(): RemoteKey? {
        return database.remoteKeyDao().remoteKeyByQuery(searchQuery)
            .firstOrNull()//.redditKeysDao().getRedditKeys().firstOrNull()

    }

//class SearchResultSource(val apiKey: String, val searchQuery: String)
//    : PagingSource<Int, SearchItem>() {
//
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItem> {
//        try {
//            val currentLoadingPageKey = params.key ?: 1
//            lateinit var response:Response<ResponseSearch>
//            if (NetworkStateHolder.isConnected) {
//                response = MovieListApi.retrofitMovie.getMovieSearchList(
//                        apikey = apiKey,
//                        pageNumber = currentLoadingPageKey,
//                        searchField = searchQuery
//                )
//            }else{
//
//            }
//            val responseData = mutableListOf<SearchItem>()
//
//            val data = response.body()?.search ?: emptyList()
//            data.let {
//                responseData.addAll(data.toMutableList())
//            }
//            val prevKey = currentLoadingPageKey.minus(1)
//            return LoadResult.Page(
//                    data = responseData,
//                    prevKey = null,
//                    nextKey = currentLoadingPageKey.plus(1)
//            )
//        } catch (exception: IOException) {
//            return LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            return LoadResult.Error(exception)
//        }
//
//    }
}