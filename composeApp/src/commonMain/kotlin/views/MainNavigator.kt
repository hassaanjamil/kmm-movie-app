package views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigator() {
    val navHostController: NavHostController = rememberNavController()
    val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

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
