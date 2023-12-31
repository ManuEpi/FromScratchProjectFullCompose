package com.manuepi.fromscratchprojectv2.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manuepi.fromscratchprojectv2.common.Screens
import com.manuepi.fromscratchprojectv2.common.SharedNavigator
import com.manuepi.fromscratchprojectv2.feature.detail.DetailScreen
import com.manuepi.fromscratchprojectv2.feature.home.HomeScreen
import com.manuepi.fromscratchprojectv2.feature.splashscreen.SplashScreen


@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    startDestination: String = Screens.Home.route
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val sharedNavigator = SharedNavigator(navController = navController)

    NavHost(
        navController = navController, startDestination = startDestination
    )
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
        composable(Screens.Home.route) { HomeScreen(sharedNavigator = sharedNavigator) }
        composable(Screens.NewsDetail.route) { DetailScreen(sharedNavigator = sharedNavigator) }
    }
}