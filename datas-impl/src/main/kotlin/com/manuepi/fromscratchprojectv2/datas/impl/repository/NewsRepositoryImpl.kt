package com.manuepi.fromscratchprojectv2.datas.impl.repository

import com.manuepi.fromscratchprojectv2.datas.NewsRepository
import com.manuepi.fromscratchprojectv2.datas.impl.api.NewsApi
import com.manuepi.fromscratchprojectv2.datas.impl.repository.mappers.NewsMapperRepositoryModel
import com.manuepi.fromscratchprojectv2.datas.models.NewsRepositoryStateResponseModel
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsMapperRepositoryModel: NewsMapperRepositoryModel
) : NewsRepository {

    override suspend fun getNews(language: String): NewsRepositoryStateResponseModel {
        // We get response from service
        val response = newsApi.getNews(language = language)

        // And we return it to our usecase
        return newsMapperRepositoryModel.mapNewsToRepository(response)
    }
}