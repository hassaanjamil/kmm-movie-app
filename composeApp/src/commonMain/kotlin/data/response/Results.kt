package data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Results(
    var adult: Boolean? = null,
    var gender: Int? = null,
    var id: Int? = null,
    @SerialName("known_for_department") var knownForDepartment: String? = null,
    var name: String? = null,
    @SerialName("original_name") var originalName: String? = null,
    var popularity: Double? = null,
    @SerialName("profile_path") var profilePath: String? = null,
    @SerialName("known_for") var knownFor: ArrayList<KnownFor> = arrayListOf(),
)
