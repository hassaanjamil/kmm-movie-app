package views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.app.kmm.movieapp.resources.MontserratAlternates_Bold
import com.app.kmm.movieapp.resources.Res
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import views.components.home.MovieList
import views.components.theme.primaryDark
import views.viewModels.HomeViewModel

@Composable
fun FavoriteScreen(viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel { HomeViewModel() }) {
    Column(
        modifier = Modifier.fillMaxSize().background(primaryDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        fetchFavoriteMovies(viewModel)
        val movies = viewModel.favoriteMovies.collectAsState().value
        if (movies.isNotEmpty()) {
            MovieList(viewModel, movies, onFavoritePress = { isFavorite, id ->
                viewModel.setMovieItemFavorite(isFavorite = if (isFavorite) 1 else 0, id = id)
            })
        } else {
            Text(
                "No Favorite Data",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Bold))
            )
        }
    }
}

@Composable
fun fetchFavoriteMovies(viewModel: HomeViewModel) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(true) {
        scope.launch {
            viewModel.fetchAllFavoriteMovies()
        }
    }
}
