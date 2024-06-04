package views.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import data.Routes
import views.FavoriteScreen
import views.HomeScreen
import views.ProfileScreen

@Composable
fun MyNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(Routes.Home.route) {
            //call our composable screens here
            HomeScreen()
        }
        composable(Routes.Search.route) {
            //call our composable screens here
            FavoriteScreen(navController)
        }
        composable(Routes.Profile.route) {
            //call our composable screens here
            ProfileScreen(navController)
        }
    }
}
