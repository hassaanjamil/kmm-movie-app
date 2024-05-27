package views.components.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigator() {
    // by using the rememberNavController() we can get the instance of the navController
    val navController = rememberNavController()
    //initializing the default selected item
    var navigationSelectedItem by remember {
        mutableStateOf(0)
    }

    //scaffold to hold our bottom navigation Bar
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar(navigationSelectedItem)
        },
        bottomBar = {
            MyNavigationBar(
                navController,
                navigationSelectedItem = navigationSelectedItem
            ) { index ->
                navigationSelectedItem = index
            }
        }
    ) { paddingValues ->
        MyNavHost(navController = navController, paddingValues = paddingValues)
    }
}
