package ir.saeedbahari.mobisoft.viewmodels.fragments

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.*
import ir.saeedbahari.mobisoft.database.dataModels.ResponseDetails
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem
import ir.saeedbahari.mobisoft.repository.MoviesDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MovieDetailsVM(
    application: Application,
    val moviesRepository: MoviesDetailsRepository
) : AndroidViewModel(application) {
    private val TAG = MovieDetailsVM::class.java.simpleName
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    val list: LiveData<ResponseDetails>
        get() = moviesRepository.responseMovieDetails
    fun getDetails(apiKey:String ="4f588b70",movieID: String) {
        moviesRepository.getMovieDetails(apiKey, movieID)

    }
    fun getDetails(item: SearchItem) {
        moviesRepository.getMovieDetails( imdbID = item.imdbID!!)
    }

    class MovieDetailsVMFactory(private val mApplication: Application) :
        ViewModelProvider.NewInstanceFactory() {
        private val mRepository: MoviesDetailsRepository =
            MoviesDetailsRepository()

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailsVM(
                mApplication,
                mRepository
            ) as T
        }
    }

    companion object {
        private const val  ITEM_ARGUMENT ="item"
        fun createArguments(item: SearchItem): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(ITEM_ARGUMENT, item)
            return bundle
        }

    }
    fun loadArguments(arguments: Bundle?) {
        if (arguments == null) {
            return
        }

        val item: SearchItem = arguments.get(ITEM_ARGUMENT) as SearchItem
        getDetails(item)

    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}