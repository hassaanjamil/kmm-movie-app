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
import views.components.theme.primaryDark
import views.viewModels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel { HomeViewModel() }) {
    Column(
        modifier = Modifier.fillMaxSize().background(primaryDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val scope = rememberCoroutineScope()
        LaunchedEffect(true) {
            scope.launch {
                try {
                    val response = viewModel.fetchApiResponse("https://ktor.io/docs/")
                    viewModel.setApiResponse(response)
                } catch (e: Exception) {
                    viewModel.setApiResponse(e.message ?: "error")
                }
            }
        }
        // TODO Movie List Screen with 2 columns and pagination
        Text(
            text = viewModel.apiResponse.collectAsState().value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Bold))
        )
        // navHostController.navigate(Route.Profile.name)
    }
}
