package data

sealed class Routes(val route: String) {
    data object Home : Routes("home_route")
    data object Search : Routes("favorite_route")
    data object Profile : Routes("profile_route")
}
