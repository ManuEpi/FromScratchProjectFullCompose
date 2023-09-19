package com.manuepi.fromscratchprojectv2.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route)
    {
        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        //composable(Screens.Home.route") { HomeScreen(navController = navController) }
    }
}


@Composable
fun SplashScreen(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "SplashScreen")
    }
}