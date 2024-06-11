package utils

import data.Movie
import data.db.MovieDto
import data.response.Results
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun Movie.toMovieDto(): MovieDto {
    return MovieDto(
        this.id,
        this.name,
        this.image,
        this.year ?: "",
        getCurrentDateTimeStamp(),
        if (this.isFavorite == true) 1 else 0
    )
}

fun MovieDto.toMovie(): Movie {
    return Movie(this.id, this.name, this.imagePath, "1.1", this.release, this.isFavorite == 1L)
}

fun Results.toMovieDto(): MovieDto {
    return MovieDto(
        this.id?.toLong()!!,
        this.title!!,
        this.posterPath!!,
        this.releaseDate ?: "",
        getCurrentDateTimeStamp(),
        if (this.isFavorite) 1 else 0
    )
}

fun getCurrentDateTimeStamp(): String {
    val currentMoment = Clock.System.now()
    val datetime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
    return "${datetime.date} ${
        datetime.hour.toString().padStart(2, '0')
    }:${datetime.minute.toString().padStart(2, '0')}:${datetime.second.toString().padStart(2, '0')}"
}
