package com.manuepi.fromscratchprojectv2.common

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class SharedNavigator(private val navController: NavHostController) : ViewModel() {
    fun updateScreenState(screen: Screens) {
        navController.navigate(screen.route)
    }

    fun goBack() {
        navController.popBackStack()
    }
}