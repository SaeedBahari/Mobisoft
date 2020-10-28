package ir.saeedbahari.mobisoft.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utechia.depotchecklist.network.CurrentStatus
import ir.saeedbahari.mobisoft.database.dataModels.ResponseDetails
import ir.saeedbahari.mobisoft.network.MovieListApi
import ir.saeedbahari.mobisoft.network.NoConnectivityException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okio.IOException
import java.net.SocketTimeoutException

class MoviesDetailsRepository() {

    private lateinit var mRepository: MoviesDetailsRepository

    fun getInstance(): MoviesDetailsRepository {
        if (mRepository == null)
            mRepository = MoviesDetailsRepository()
        return mRepository
    }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val _statusMovieDetails = MutableLiveData<CurrentStatus>()
    val statusMovieDetails: LiveData<CurrentStatus>
        get() = _statusMovieDetails

    private val _responseMovieDetails = MutableLiveData<ResponseDetails>()
    val responseMovieDetails: LiveData<ResponseDetails>
        get() = _responseMovieDetails

    fun getMovieDetails(
        apiKey: String ="4f588b70",
        imdbID: String
    ) {
        coroutineScope.launch {
            _statusMovieDetails.postValue(CurrentStatus.LOADING)
            try {
                val result = MovieListApi.retrofitMovie.getMovieDetails(apikey = apiKey,imdbID = imdbID)
                if (result.isSuccessful) {
                    _responseMovieDetails.postValue(result.body())
                } else {

                }
            } catch (e: Throwable) {
                when (e) {
                    is NoConnectivityException -> _statusMovieDetails.postValue(CurrentStatus.INTERNET_ERROR)
                    is SocketTimeoutException -> _statusMovieDetails.postValue(CurrentStatus.TIME_OUT_ERROR)
                    is IOException -> _statusMovieDetails.postValue(CurrentStatus.INTERNET_ERROR)
                    else -> _statusMovieDetails.postValue(CurrentStatus.ERROR)
                }
            }
        }

    }
}