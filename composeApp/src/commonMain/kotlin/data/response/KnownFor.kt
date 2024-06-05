package data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class KnownFor(
    @SerialName("backdrop_path") var backdropPath: String? = null,
    var id: Int? = null,
    @SerialName("original_name") var originalName: String? = null,
    var overview: String? = null,
    @SerialName("poster_path") var posterPath: String? = null,
    @SerialName("media_type") var mediaType: String? = null,
    var adult: Boolean? = null,
    var name: String? = null,
    @SerialName("original_language") var originalLanguage: String? = null,
    @SerialName("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
    var popularity: Double? = null,
    @SerialName("first_air_date") var firstAirDate: String? = null,
    @SerialName("vote_average") var voteAverage: Double? = null,
    @SerialName("vote_count") var voteCount: Int? = null,
    @SerialName("origin_country") var originCountry: ArrayList<String> = arrayListOf(),
)
