package navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import views.HomeScreen
import views.ProfileScreen

@Composable
fun HomeNavigator() {
    val navHostController: NavHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Route.Home.name,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        composable(route = Route.Home.name) {
            HomeScreen(navHostController = navHostController)
        }
        composable(route = Route.Profile.name) {
            ProfileScreen(navHostController = navHostController)
        }
    }
}

enum class Route {
    Home, Profile
}
