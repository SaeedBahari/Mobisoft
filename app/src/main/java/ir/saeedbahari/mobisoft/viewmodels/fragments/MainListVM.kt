package ir.saeedbahari.mobisoft.viewmodels.fragments

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ir.saeedbahari.mobisoft.database.MovieDatabase
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem
import ir.saeedbahari.mobisoft.repository.MoviesListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

class MainListVM(
    application: Application,
    val moviesRepository: MoviesListRepository
) : AndroidViewModel(application) {
    private val TAG = MainListVM::class.java.simpleName
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val _responselist = MutableLiveData<Flow<PagingData<SearchItem>>>()
    val list: LiveData<Flow<PagingData<SearchItem>>>
        get() = _responselist
    fun getList(apiKey:String ="4f588b70",searchquery: String) {
        _responselist.postValue(moviesRepository.getSearchResultStream(apiKey, searchquery).cachedIn(viewModelScope))
    }

    class MainListVMFactory(private val mApplication: Application) :
        ViewModelProvider.NewInstanceFactory() {
        private val mRepository: MoviesListRepository =
            MoviesListRepository(mApplication)
//        private val mDBRepository: MovieDatabase

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainListVM(
                mApplication,
                mRepository
            ) as T
        }
//        init {
//            val movieDao = MovieDatabase.getDatabase(mApplication).movieDao()
//            mDBRepository = MovieDatabase(movieDao, Applications.context)
//        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}