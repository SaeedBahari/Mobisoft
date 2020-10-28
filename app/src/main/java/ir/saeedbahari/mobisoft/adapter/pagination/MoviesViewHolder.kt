package ir.saeedbahari.mobisoft.adapter.pagination

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textview.MaterialTextView
import ir.saeedbahari.mobisoft.R
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem
import ir.saeedbahari.mobisoft.databinding.ItemMoviesListBinding
import ir.saeedbahari.mobisoft.interfaces.MovieItemClick

class MoviesViewHolder(
//    view: View,
    val binding: ItemMoviesListBinding,
    itemClick: MovieItemClick
) : RecyclerView.ViewHolder(binding.root) {
    private val _tAG = MoviesViewHolder::class.java.simpleName
    val movieTitle: MaterialTextView = itemView.findViewById(R.id.title_movie_list_item_txt)
    val productCheckoutIcon: AppCompatImageView = itemView.findViewById(R.id.thumb_movie_list_item_img)

    private lateinit var searchItem: SearchItem
    private var itemClickListener: MovieItemClick = itemClick

    init {
        binding.root.setOnClickListener {
            itemClickListener.itemClickListener(searchItem.imdbID.toString(),searchItem)

        }
    }

    fun bind(movieItem: SearchItem?) {

        if (movieItem == null) {
        } else {
            showRepoData(movieItem)
        }
    }

    private fun showRepoData(movieItem: SearchItem) {
        this.searchItem = movieItem
        movieTitle.text = movieItem.title
        Glide.with(binding.root.context)
            .asBitmap()
            .load(movieItem.poster)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.item_defult_placeholder)
            .into(binding.thumbMovieListItemImg)
    }
}