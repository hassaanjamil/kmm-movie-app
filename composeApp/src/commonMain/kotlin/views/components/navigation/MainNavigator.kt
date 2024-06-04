package views.components.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import views.viewModels.MainViewModel

@Composable
fun MainNavigator(viewModel: MainViewModel = viewModel { MainViewModel() }) {
    // by using the rememberNavController() we can get the instance of the navController
    val navController = rememberNavController()
    val stateNavigationIndex: State<Int> = viewModel.navigationIndex.collectAsState();

    //scaffold to hold our bottom navigation Bar
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar(stateNavigationIndex.value)
        },
        bottomBar = {
            MyBottomNavigationBar(
                navController,
                navigationSelectedItem = stateNavigationIndex.value
            ) { index ->
                viewModel.setNavigationIndex(index)
            }
        }
    ) { paddingValues ->
        MyNavHost(navController = navController, paddingValues = paddingValues)
    }
}
