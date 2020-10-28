package ir.saeedbahari.mobisoft.database.dataModels

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Parcelize
@Entity
data class SearchItem(

	@Json(name="Type")
	val type: String? = null,

	@Json(name="Year")
	val year: String? = null,

	@Json(name="imdbID")
	val imdbID: String? = null,

	@Json(name="Poster")
	val poster: String? = null,

	@Json(name="Title")
	val title: String? = null
) : Parcelable,Serializable {
	@PrimaryKey(autoGenerate = true)
	var id: Long = 0
}