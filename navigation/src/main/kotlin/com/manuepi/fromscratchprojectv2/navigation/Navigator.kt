package com.manuepi.fromscratchprojectv2.navigation

import androidx.navigation.NavHostController
import dagger.hilt.android.scopes.ActivityRetainedScoped

@ActivityRetainedScoped
class Navigator {
    private lateinit var _navController: NavHostController

    fun navigate(destination: Screens) {
        _navController.navigate(destination.route)
    }

    fun setController(controller: NavHostController) {
        _navController = controller
    }
}