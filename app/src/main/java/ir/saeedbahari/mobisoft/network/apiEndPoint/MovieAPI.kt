package ir.saeedbahari.mobisoft.network.apiEndPoint

import ir.saeedbahari.mobisoft.database.dataModels.ResponseDetails
import ir.saeedbahari.mobisoft.network.apiModels.ResponseSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET(".")
    suspend fun getMovieSearchList(@Query("apikey") apikey:String, @Query("s") searchField:String, @Query("page") pageNumber: Int)
            : Response<ResponseSearch>
    @GET(".")
    suspend fun getMovieDetails(@Query("apikey") apikey:String, @Query("i") imdbID:String)
            : Response<ResponseDetails>
}