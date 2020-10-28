package ir.saeedbahari.mobisoft.adapter.pagination

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import ir.saeedbahari.mobisoft.R
import ir.saeedbahari.mobisoft.databinding.ItemFooterLayoutBinding

class MoviesLoadStateViewHolder(
    private val binding: ItemFooterLayoutBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val _tAG: String = MoviesLoadStateViewHolder::class.java.simpleName

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        Log.e("$_tAG loadState",loadState.toString())
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): MoviesLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_footer_layout, parent, false)
            val binding = ItemFooterLayoutBinding.bind(view)
            return MoviesLoadStateViewHolder(binding, retry)
        }
    }
}