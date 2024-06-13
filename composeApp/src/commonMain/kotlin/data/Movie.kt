package data

data class Movie(
    val id: Long,
    val name: String,
    val image: String,
    val releaseDate: String? = null,
    var isFavorite: Boolean? = false,
)
