package com.manuepi.fromscratchprojectv2.datas.di

import com.manuepi.fromscratchprojectv2.datas.NewsLocalCache
import com.manuepi.fromscratchprojectv2.datas.NewsRepository
import com.manuepi.fromscratchprojectv2.datas.impl.api.NewsApi
import com.manuepi.fromscratchprojectv2.datas.impl.api.NewsApiImpl
import com.manuepi.fromscratchprojectv2.datas.impl.cache.NewsLocalCacheImpl
import com.manuepi.fromscratchprojectv2.datas.impl.network.NewsNetwork
import com.manuepi.fromscratchprojectv2.datas.impl.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Dependency Injection for api, repository and network in order to include them as parameters
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class NewsModule {

    private val BASE_URL = "https://newsapi.org"

    @ActivityRetainedScoped
    @Provides
    fun providesNewsApi(impl: NewsApiImpl): NewsApi = impl

    @ActivityRetainedScoped
    @Provides
    fun providesNewsRepository(impl: NewsRepositoryImpl): NewsRepository = impl

    @ActivityRetainedScoped
    @Provides
    fun providesNewsNetwork(): NewsNetwork {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsNetwork::class.java)
    }

    @ActivityRetainedScoped
    @Provides
    fun provideNewsLocalCacheImpl(impl: NewsLocalCacheImpl): NewsLocalCache = impl

}