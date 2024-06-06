package views.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.app.kmm.movieapp.resources.MontserratAlternates_Bold
import com.app.kmm.movieapp.resources.Res
import data.response.Results
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font

@Composable
fun MovieItem(data: Results) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Row(modifier = Modifier.padding(16.dp)) {
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
                color = Color.White,
                fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Bold))
            )
            Text(
                text = data.releaseDate!!,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Bold))
            )

        }

    }
}
