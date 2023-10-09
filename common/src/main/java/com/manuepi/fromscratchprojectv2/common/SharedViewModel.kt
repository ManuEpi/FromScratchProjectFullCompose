package com.manuepi.fromscratchprojectv2.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController

class SharedViewModel(val navController: NavHostController) : ViewModel() {
    fun updateScreenState(screen: Screens) {
        navController.navigate(screen.route)
    }

}

class SharedViewModelFactory(
    private val navController: NavHostController,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(navController) as T
        }
        throw IllegalStateException()
    }
}