package data.db

import app.cash.sqldelight.db.SqlDriver

class MovieDatabase(private val driver: SqlDriver) {
    private var database: Database = Database(driver)
    private var queries: DatabaseQueries = database.databaseQueries
    fun insertFavoriteMovie(movieDto: MovieDto) {
        queries.insertMovieObj(movieDto)
    }

    fun deleteFavoriteMovie(id: Long) {
        queries.deleteMovie(id)
    }

    fun getMovieById(id: Long): MovieDto? {
        return queries.getMovieById(id).executeAsOneOrNull()
    }

    fun getAllFavoriteMovies(): List<MovieDto> {
        return queries.selectAllMovies().executeAsList()
    }
}
