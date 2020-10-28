package ir.saeedbahari.mobisoft.network.apiModels

import com.squareup.moshi.Json
import ir.saeedbahari.mobisoft.database.dataModels.SearchItem

data class ResponseSearch(

	@Json(name="Response")
	val response: String? = null,

	@Json(name="totalResults")
	val totalResults: String? = null,

	@Json(name="Search")
	val search: List<SearchItem>? = null
)


