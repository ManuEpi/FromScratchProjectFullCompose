package com.manuepi.fromscratchprojectv2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manuepi.fromscratchprojectv2.feature.home.HomeScreen

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object Home : Screens("home_screen")
}

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navigator: Navigator,
    startDestination: String = Screens.SplashScreen.route
) {
    val navController = rememberNavController()
    navigator.setController(navController)

    NavHost(navController = navController, startDestination = Screens.Home.route)
    {
        /*composable(Screens.SplashScreen.route) {
            SplashScreen(
                isLoaded = true, // TODO remplacer
                onSplashEndedValid = {
                    navController.navigate(Screens.Home.route)
                },
                onSplashEndedInvalid = {
                    Toast.makeText(
                        context,
                        "Une erreur est survenue",
                        Toast.LENGTH_SHORT
                    ).show()
                })
        }*/
        composable(Screens.Home.route) { HomeScreen() }
    }
}