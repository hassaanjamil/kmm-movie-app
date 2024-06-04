package views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import com.app.kmm.movieapp.resources.MontserratAlternates_Bold
import com.app.kmm.movieapp.resources.Res
import data.Greeting
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import views.components.theme.primaryDark

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().background(primaryDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val scope = rememberCoroutineScope()
        var text by remember { mutableStateOf("Loading") }
        LaunchedEffect(true) {
            scope.launch {
                text = try {
                    Greeting().greeting()
                } catch (e: Exception) {
                    e.message ?: "error"
                }
            }
        }
        // TODO Movie List Screen with 2 columns and pagination
        Text(
            text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Bold))
        )
        // navHostController.navigate(Route.Profile.name)
    }
}
