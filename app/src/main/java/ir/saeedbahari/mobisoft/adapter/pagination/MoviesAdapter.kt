package ir.saeedbahari.mobisoft.adapter.pagination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem
import ir.saeedbahari.mobisoft.databinding.ItemMoviesListBinding
import ir.saeedbahari.mobisoft.interfaces.MovieItemClick

class MoviesAdapter(val itemClick: MovieItemClick) : PagingDataAdapter<SearchItem, MoviesViewHolder>(
    DataDifferntiator
) {
lateinit var bindind:ItemMoviesListBinding
    object DataDifferntiator : DiffUtil.ItemCallback<SearchItem>() {

        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        bindind = ItemMoviesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MoviesViewHolder(
            bindind,
            itemClick
        )
    }


}