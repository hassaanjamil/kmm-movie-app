package views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import views.components.home.MovieItem
import views.components.theme.primaryDark
import views.viewModels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel { HomeViewModel() }) {
    Column(
        modifier = Modifier.fillMaxSize().background(primaryDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        fetchPopularMovies(viewModel)
        val movieResponse = viewModel.movieResponse.collectAsState().value
        LazyColumn {
            items(items = movieResponse.results) { item ->
                MovieItem(item)
            }
        }
    }
}

@Composable
fun fetchPopularMovies(viewModel: HomeViewModel) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(true) {
        scope.launch {
            try {
                val params = hashMapOf(
                    "language" to "en-US",
                    "page" to "1",
                    "api_key" to "5b16101d466cdc0b3d0314c28dfb420b"
                )
                val response = viewModel.fetchApiResponse(
                    "https://api.themoviedb.org/3/movie/popular",
                    params
                )
                println(response)
                response.let { viewModel.setMovieResponse(it) }
            } catch (e: Exception) {
                viewModel.setApiResponse(e.message ?: "error")
            }
        }
    }
}
