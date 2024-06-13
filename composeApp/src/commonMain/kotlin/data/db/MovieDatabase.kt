package data.db

import app.cash.sqldelight.db.SqlDriver

class MovieDatabase(private val driver: SqlDriver) {
    private var database: Database = Database(driver)
    private var queries: DatabaseQueries = database.databaseQueries
    fun insertMovie(movieDto: MovieDto) {
        queries.insertMovieObj(movieDto)
    }

    fun updateFavoriteById(isFavorite: Long, id: Long) {
        queries.updateFavoriteById(isFavorite, id)
    }

    fun deleteMovie(id: Long) {
        queries.deleteMovie(id)
    }

    fun getMovieById(id: Long): MovieDto? {
        return queries.getMovieById(id).executeAsOneOrNull()
    }

    fun getAllMovies(): List<MovieDto> {
        return queries.selectAllMovies().executeAsList()
    }
}
