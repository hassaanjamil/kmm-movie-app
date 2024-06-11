package views.viewModels

import androidx.lifecycle.ViewModel
import data.db.DriverFactory
import data.db.MovieDatabase
import data.response.MovieResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import repository.HomeRepository
import utils.toMovieDto

class HomeViewModel : ViewModel() {
    private val _navigationIndex = MutableStateFlow(0)
    val navigationIndex: StateFlow<Int> = _navigationIndex.asStateFlow()
    fun setNavigationIndex(index: Int) {
        _navigationIndex.value = index
    }

    private val _apiResponse = MutableStateFlow("Loading")
    val apiResponse: StateFlow<String> = _apiResponse.asStateFlow()
    fun setApiResponse(response: String) {
        _apiResponse.value = response
    }

    private val _movieResponse = MutableStateFlow(MovieResponse())
    var movieResponse: StateFlow<MovieResponse> = _movieResponse.asStateFlow()

    val db = MovieDatabase(DriverFactory().createDriver())
    private fun setMovieResponse(response: MovieResponse) {
        _movieResponse.value = response
    }

    fun setMovieItemFavorite(id: Int, favorite: Boolean) {
        // Get the current movie response
        val currentResponse = _movieResponse.value.copy()
        // Update the list of movies with the new favorite status for the specified movie
        currentResponse.results = currentResponse.results.map { movie ->
            if (movie.id == id) {
                movie.copy(isFavorite = favorite)
            } else {
                movie
            }
        }
        // Set the updated movie response
        setMovieResponse(currentResponse)

        // Insert favorite into db
        val favorite = currentResponse.results.find { it.id == id }
        if (favorite != null) {
            val favMovieDb = db.getMovieById(favorite.id?.toLong()!!)
            if (favMovieDb == null) {
                db.insertFavoriteMovie(favorite.toMovieDto())
            } else {
                db.deleteFavoriteMovie(favorite.id?.toLong()!!)
            }
        }

        println(db.getAllFavoriteMovies())
    }

    private val homeRepository = HomeRepository()
    suspend fun fetchApiResponse(
        url: String,
        hashMap: HashMap<String, String> = hashMapOf(),
    ) {
        try {
            val response = homeRepository.fetchApiResponse(url, hashMap)
            setMovieResponse(response)
        } catch (e: Exception) {
            setApiResponse(e.message ?: "error")
        }
    }
}
