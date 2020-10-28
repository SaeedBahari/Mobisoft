package ir.saeedbahari.mobisoft.interfaces

import ir.saeedbahari.mobisoft.database.dataModels.SearchItem

interface MovieItemClick {
    fun itemClickListener(imdbID: String,item: SearchItem)
}