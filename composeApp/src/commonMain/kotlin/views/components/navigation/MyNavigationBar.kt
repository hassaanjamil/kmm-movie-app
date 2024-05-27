package views.components.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import data.BottomNavigationItem

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
