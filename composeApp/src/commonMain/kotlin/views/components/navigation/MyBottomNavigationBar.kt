package views.components.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.app.kmm.movieapp.resources.MontserratAlternates_Regular
import com.app.kmm.movieapp.resources.Res
import data.BottomNavigationItem
import org.jetbrains.compose.resources.Font

@Composable
fun MyBottomNavigationBar(
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
                    label = {
                        Text(
                            navigationItem.label,
                            fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                        )
                    },
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
