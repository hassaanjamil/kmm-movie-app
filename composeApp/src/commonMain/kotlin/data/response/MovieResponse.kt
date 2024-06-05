package data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieResponse(

    var page: Int? = null,
    var results: ArrayList<Results> = arrayListOf(),
    @SerialName("total_pages") var totalPages: Int? = null,
    @SerialName("total_results") var totalResults: Int? = null,

    )
