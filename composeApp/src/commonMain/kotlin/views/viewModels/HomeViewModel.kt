package views.viewModels

import androidx.lifecycle.ViewModel
import data.Movie
import data.db.DriverFactory
import data.db.MovieDatabase
import data.db.MovieDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import repository.HomeRepository
import utils.toListMovie
import utils.toMovie
import utils.toMovieDto

class HomeViewModel : ViewModel() {
    private val _apiResponse = MutableStateFlow("Loading")
//    val apiResponse: StateFlow<String> = _apiResponse.asStateFlow()

    private val _movies = MutableStateFlow(listOf<Movie>())
    var movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _favoriteMovies = MutableStateFlow(listOf<Movie>())
    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies.asStateFlow()

    val db = MovieDatabase(DriverFactory().createDriver())
    private val homeRepository = HomeRepository()

    suspend fun fetchPopularMovies(
        url: String,
        hashMap: HashMap<String, String> = hashMapOf(),
    ) {
        try {
            val response = homeRepository.fetchApiResponse(url, hashMap)
            val movies = response.results.map { result -> result.toMovie() }
            movies.forEach { movie -> insertMovie(movie.toMovieDto()) }
            fetchAllMovies()
        } catch (e: Exception) {
            setMovies(e.message ?: "error")
        }
    }

    private fun setMovies(message: String) {
        _apiResponse.value = message
    }

    private fun setMovies(movies: List<Movie>) {
        _movies.value = movies
    }

    fun setMovieItemFavorite(isFavorite: Long, id: Long) {
        // Insert favorite into db
        updateFavoriteById(isFavorite = isFavorite, id = id)
        fetchAllMovies()
        fetchAllFavoriteMovies()
    }

    private fun insertMovie(movieDto: MovieDto) {
        db.insertMovie(movieDto)
    }

    private fun updateFavoriteById(isFavorite: Long, id: Long) {
        db.updateFavoriteById(isFavorite, id)
    }

    private fun deleteMovie(id: Long) {
        db.deleteMovie(id)
    }

    fun getAllMovies(): List<Movie> {
        return db.getAllMovies().map { movie -> movie.toMovie() }
    }

    fun fetchAllMovies() {
        setMovies(db.getAllMovies().map { item -> item.toMovie() })
    }

    fun fetchAllFavoriteMovies() {
        _favoriteMovies.value =
            db.getAllMovies().toListMovie().filter { item -> item.isFavorite == true }
    }
}
