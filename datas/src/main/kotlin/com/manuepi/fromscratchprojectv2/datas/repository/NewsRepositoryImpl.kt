package com.manuepi.fromscratchprojectv2.datas.repository

import com.manuepi.fromscratchprojectv2.datas.api.NewsApi
import com.manuepi.fromscratchprojectv2.datas.repository.mappers.NewsMapperRepositoryModel
import com.manuepi.fromscratchprojectv2.datas.repository.models.NewsRepositoryStateResponseModel
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsMapperRepositoryModel: NewsMapperRepositoryModel
) : NewsRepository {

    override suspend fun getNews(language: String, word: String): NewsRepositoryStateResponseModel {
        // We get response from service
        val response = newsApi.getNews(language = language, word = word)

        // And we return it to our usecase
        return newsMapperRepositoryModel.mapNewsToRepository(response)
    }
}