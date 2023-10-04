package com.manuepi.fromscratchprojectv2.navigation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class NavigationModule {

    @Provides
    fun providesNavigation() = Navigator()
}