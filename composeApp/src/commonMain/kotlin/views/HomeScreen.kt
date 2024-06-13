package views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import views.components.home.MovieList
import views.components.theme.primaryDark
import views.viewModels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel { HomeViewModel() }) {
    fetchPopularMovies(viewModel)
    val movies = viewModel.movies.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize().background(primaryDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MovieList(viewModel, movies, onFavoritePress = { isFavorite, id ->
            viewModel.setMovieItemFavorite(isFavorite = if (isFavorite) 1 else 0, id = id)
        })
    }
}

@Composable
fun fetchPopularMovies(viewModel: HomeViewModel) {
    val scope = rememberCoroutineScope()
    if (viewModel.getAllMovies().isEmpty()) {
        println("FETCHING MOVIES DATA FROM REMOTE SOURCE...");
        LaunchedEffect(true) {
            scope.launch {
                val params = hashMapOf(
                    "language" to "en-US",
                    "page" to "1",
                    "api_key" to "5b16101d466cdc0b3d0314c28dfb420b"
                )
                viewModel.fetchPopularMovies(
                    "https://api.themoviedb.org/3/movie/popular",
                    params
                )
            }
        }
    } else {
        viewModel.fetchAllMovies()
    }
}
