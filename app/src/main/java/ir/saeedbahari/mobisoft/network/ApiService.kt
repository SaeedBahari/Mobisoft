package ir.saeedbahari.mobisoft.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ir.saeedbahari.mobisoft.network.apiEndPoint.MovieAPI
import ir.saeedbahari.mobisoft.utils.MovieAppObj
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/*
log to see http request and url
 */
val interceptorLog = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val apiClient = OkHttpClient().newBuilder().connectTimeout(40, TimeUnit.SECONDS)
    .readTimeout(40, TimeUnit.SECONDS)
    .addInterceptor(interceptorLog)
    .addInterceptor(HeaderInterceptor())
    .build()


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitMoshi = Retrofit.Builder()
    .client(apiClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(MovieAppObj.BASE_URL)
    .build()

object MovieListApi {

    val retrofitMovie: MovieAPI by lazy {
        retrofitMoshi.create(MovieAPI::class.java)
    }

}