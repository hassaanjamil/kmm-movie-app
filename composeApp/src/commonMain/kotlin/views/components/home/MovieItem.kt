package views.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.app.kmm.movieapp.resources.MontserratAlternates_Bold
import com.app.kmm.movieapp.resources.Res
import com.app.kmm.movieapp.resources.favorite
import data.response.Results
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieItem(data: Results, onFavoritePress: (id: Int, favorite: Boolean) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var isFavorite by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
            KamelImage(
                modifier = Modifier.height(100.dp).width(100.dp),
                resource = asyncPainterResource(data = "https://image.tmdb.org/t/p/w500/${data.posterPath}"),
                contentDescription = data.originalTitle,
                onLoading = { progress ->
                    CircularProgressIndicator(progress = {
                        progress
                    })
                },
                onFailure = { exception ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = exception.message.toString(),
                            actionLabel = "Hide",
                            duration = SnackbarDuration.Short
                        )
                    }
                })
            Column {
                Text(
                    text = data.originalTitle!!,
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Bold))
                )
                Text(
                    text = data.releaseDate!!,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Bold))
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Icon(
                        imageVector = if (data.isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        contentDescription = stringResource(Res.string.favorite),
                        modifier = Modifier.padding(5.dp).clickable {
                            isFavorite = !isFavorite
                            onFavoritePress(data.id!!, isFavorite)
                        },
                        tint = if (data.isFavorite) Color.Red else Color.Gray,
                    )
                }

            }

        }
    }

}
