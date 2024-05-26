package views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kmmmovieapp.composeapp.generated.resources.Res
import kmmmovieapp.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun KMMLogoMsg (modifier: Modifier = Modifier, name: String, greeting: String) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painterResource(Res.drawable.compose_multiplatform), null)
        Text("Compose: $name $greeting")
    }
}
