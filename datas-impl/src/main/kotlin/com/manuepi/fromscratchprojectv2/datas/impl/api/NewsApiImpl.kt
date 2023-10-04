package com.manuepi.fromscratchprojectv2.datas.impl.api

import com.manuepi.fromscratchprojectv2.common.NetworkResponse
import com.manuepi.fromscratchprojectv2.common.safeApiCall
import com.manuepi.fromscratchprojectv2.datas.impl.api.models.NewsApiResponseModel
import com.manuepi.fromscratchprojectv2.datas.impl.network.NewsNetwork
import javax.inject.Inject

class NewsApiImpl @Inject constructor(
    private val newsNetwork: NewsNetwork
) : NewsApi {

    companion object {
        const val apiKey = "d23afaf5af8244acb3b5e4d2bc63fbe6"
    }

    override suspend fun getNews(language: String): NetworkResponse<NewsApiResponseModel> =
        // Handle service response for getNews call
        safeApiCall {
            newsNetwork.getNews(apiKey = apiKey, q = "bitcoin", language = language)
        }
}