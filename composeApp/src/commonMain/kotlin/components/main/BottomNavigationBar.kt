package components.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import data.Screens
import views.FavoriteScreen
import views.HomeScreen
import views.ProfileScreen

@Composable
fun BottomNavigationBar() {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navigationSelectedItem: Int) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                BottomNavigationItem().bottomNavigationItems()[navigationSelectedItem].label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
fun MyNavigationBar(
    navController: NavHostController,
    navigationSelectedItem: Int,
    updateNavigationIndex: (Int) -> Unit,
) {
    NavigationBar {
        //getting the list of bottom navigation items for our data class
        BottomNavigationItem().bottomNavigationItems()
            .forEachIndexed { index, navigationItem ->
                //iterating all items with their respective indexes
                NavigationBarItem(
                    selected = index == navigationSelectedItem,
                    label = { Text(navigationItem.label) },
                    icon = {
                        Icon(
                            navigationItem.icon,
                            contentDescription = navigationItem.label
                        )
                    },
                    onClick = {
                        updateNavigationIndex(index)
                        navController.navigate(navigationItem.route) {
                            navController.graph.findStartDestination().route?.let {
                                popUpTo(it) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
    }
}

@Composable
fun MyNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(Screens.Home.route) {
            //call our composable screens here
            HomeScreen(navController)
        }
        composable(Screens.Search.route) {
            //call our composable screens here
            FavoriteScreen(navController)
        }
        composable(Screens.Profile.route) {
            //call our composable screens here
            ProfileScreen(navController)
        }
    }
}
