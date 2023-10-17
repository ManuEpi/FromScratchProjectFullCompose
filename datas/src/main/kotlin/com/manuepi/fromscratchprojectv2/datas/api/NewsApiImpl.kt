package com.manuepi.fromscratchprojectv2.datas.api

import com.manuepi.fromscratchprojectv2.common.NetworkResponse
import com.manuepi.fromscratchprojectv2.common.safeApiCall
import com.manuepi.fromscratchprojectv2.datas.BuildConfig
import com.manuepi.fromscratchprojectv2.datas.api.models.NewsApiResponseModel
import com.manuepi.fromscratchprojectv2.datas.network.NewsNetwork
import javax.inject.Inject

class NewsApiImpl @Inject constructor(
    private val newsNetwork: NewsNetwork
) : NewsApi {
    override suspend fun getNews(language: String, word: String): NetworkResponse<NewsApiResponseModel> =
        // Handle service response for getNews call
        safeApiCall {
            newsNetwork.getNews(apiKey = BuildConfig.API_KEY, q = word, language = language)
        }
}