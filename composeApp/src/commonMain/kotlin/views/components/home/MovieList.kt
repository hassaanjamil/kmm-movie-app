package views.components.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import data.Movie
import views.viewModels.HomeViewModel

@Composable
fun MovieList(
    viewModel: HomeViewModel,
    movies: List<Movie>,
    onFavoritePress: (isFavorite: Boolean, id: Long) -> Unit,
) {
    LazyColumn {
        items(items = movies) { item ->
            MovieItem(item, onFavoritePress = onFavoritePress)
        }
    }
}
