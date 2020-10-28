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
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.saeedbahari.mobisoft.R
import ir.saeedbahari.mobisoft.adapter.pagination.MoviesAdapter
import ir.saeedbahari.mobisoft.adapter.pagination.MoviesFooterLoadStateAdapter
import ir.saeedbahari.mobisoft.databinding.FragmentDetailsBinding
import ir.saeedbahari.mobisoft.databinding.FragmentMainListBinding
import ir.saeedbahari.mobisoft.interfaces.MovieItemClick
import ir.saeedbahari.mobisoft.viewmodels.fragments.MainListVM
import ir.saeedbahari.mobisoft.viewmodels.fragments.MovieDetailsVM
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: MovieDetailsVM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: MovieDetailsVM.MovieDetailsVMFactory =
                MovieDetailsVM.MovieDetailsVMFactory(mApplication = requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(MovieDetailsVM::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.list.observe(viewLifecycleOwner, {
            it?.let {
                Glide.with(mContext)
                        .asBitmap()
                        .load(it.poster)
                        .placeholder(R.drawable.item_defult_placeholder)
                        .into(binding.posterDetailsImg)
                binding.rateDetailsTxt.text = it.imdbRating
                binding.actorsDetailsTxt.text = it.actors
                binding.directorDetailsTxt.text = it.director
                binding.plotDetailsTxt.text = it.plot
            }

        })
        viewModel.loadArguments(arguments)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        private lateinit var mContext: Context

        @JvmStatic
        fun newInstance() =
                DetailsFragment()

    }

}