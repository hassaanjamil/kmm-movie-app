package data

data class Movie(
    val id: Long,
    val name: String,
    val image: String,
    val rating: String,
    val year: String? = null,
    val isFavorite: Boolean? = false,
)
