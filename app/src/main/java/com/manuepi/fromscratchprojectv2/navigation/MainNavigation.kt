package com.manuepi.fromscratchprojectv2.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manuepi.feature.home.HomeScreen
import com.manuepi.feature.splashscreen.SplashScreen

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object Home : Screens("home_screen")
}

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.SplashScreen.route
) {
    val context = LocalContext.current
    //val isLoaded by mainViewModel.isLoaded.collectAsState()

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route)
    {
        composable(Screens.SplashScreen.route) {
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
        }
        composable(Screens.Home.route) { HomeScreen(navController = navController) }
    }
}