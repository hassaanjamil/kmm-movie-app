import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import views.components.navigation.MainNavigator
import views.components.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        MainNavigator()
    }
}
