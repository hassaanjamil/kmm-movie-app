package views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.kmm.movieapp.resources.MontserratAlternates_Bold
import com.app.kmm.movieapp.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TODO Movie List Screen with 2 columns and pagination
        Text(
            "Home Screen",
            color = Color.White,
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Bold))
        )
        // navHostController.navigate(Route.Profile.name)
    }
}
