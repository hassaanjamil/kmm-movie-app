package data

sealed class Screens(val route: String) {
    data object Home : Screens("home_route")
    data object Search : Screens("favorite_route")
    data object Profile : Screens("profile_route")
}
