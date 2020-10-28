package ir.saeedbahari.mobisoft.views.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import ir.saeedbahari.mobisoft.R
import ir.saeedbahari.mobisoft.adapter.pagination.MoviesAdapter
import ir.saeedbahari.mobisoft.adapter.pagination.MoviesFooterLoadStateAdapter
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem
import ir.saeedbahari.mobisoft.databinding.FragmentMainListBinding
import ir.saeedbahari.mobisoft.interfaces.MovieItemClick
import ir.saeedbahari.mobisoft.viewmodels.fragments.MainListVM
import ir.saeedbahari.mobisoft.viewmodels.fragments.MovieDetailsVM
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainListFragment : Fragment(), MovieItemClick {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMainListBinding
    private lateinit var viewModel: MainListVM
    private var searchJob: Job? = null
private lateinit var mAdapter: MoviesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val factory: MainListVM.MainListVMFactory =
            MainListVM.MainListVMFactory(mApplication = requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(MainListVM::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainListBinding.inflate(inflater, container, false)
        initAdapter()
        binding.retryButton.setOnClickListener {
            mAdapter.retry()
        }
        binding.moviesSwipeRefresh.setOnRefreshListener {
            mAdapter.refresh()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchJob?.cancel()
        viewModel.list.observe(viewLifecycleOwner, {
            it?.let {
                binding.moviesSwipeRefresh.isRefreshing = false
                searchJob = lifecycleScope.launch {
                    it.collectLatest { it2 ->
                        mAdapter.submitData(it2)
                    }

                }
            }

        })
        binding.moviesSwipeRefresh.isRefreshing = true
        viewModel.getList(searchquery = "avatar")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        private lateinit var mContext: Context
    }
    private fun initAdapter() {
        mAdapter = MoviesAdapter(this)
        binding.moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(mContext)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.withLoadStateFooter(MoviesFooterLoadStateAdapter() {
            mAdapter.retry()
        })
        /**
         * Handle All Network Error
         */
        mAdapter.addLoadStateListener { loadState ->
            binding.moviesSwipeRefresh.isRefreshing = loadState.source.refresh is LoadState.Loading
            binding.moviesRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                Toast.makeText(
                    mContext,
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
            /**
             * Show Empty List Message Center Of Page
             */
            if (loadState.append.endOfPaginationReached) {
                if (mAdapter.itemCount < 1) {
                    binding.movieListEmpty.visibility = View.VISIBLE;
                    binding.movieListEmpty.text = resources.getString(R.string.movies_list_is_empty);
                } else {
                    binding.movieListEmpty.visibility = View.GONE;
                }
            }

        }
    }

    override fun itemClickListener(imdbID: String,item:SearchItem) {
        findNavController().navigate(R.id.action_list_to_details,
        MovieDetailsVM.createArguments(item))
    }
}