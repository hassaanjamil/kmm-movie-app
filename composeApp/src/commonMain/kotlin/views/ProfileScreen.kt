package views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.app.kmm.movieapp.resources.MontserratAlternates_Bold
import com.app.kmm.movieapp.resources.Res
import org.jetbrains.compose.resources.Font
import views.components.theme.primaryDark

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(primaryDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Profile Screen",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Bold))
        )
//        if (navHostController.previousBackStackEntry != null)
//                navHostController.navigateUp()
    }
}
