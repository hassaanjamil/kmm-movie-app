package data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Results(
    var adult: Boolean? = null,
    @SerialName("backdrop_path") var backdropPath: String? = null,
    @SerialName("gender_ids") var genderIds: List<Int>? = null,
    var id: Int? = null,
    @SerialName("original_language") var originalLanguage: String? = null,
    @SerialName("original_title") var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    @SerialName("poster_path") var posterPath: String? = null,
    @SerialName("release_date") var releaseDate: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    @SerialName("vote_average") var voteAverage: Double? = null,
    @SerialName("vote_count") var voteCount: Int? = null,
    var isFavorite: Boolean = false,
)
