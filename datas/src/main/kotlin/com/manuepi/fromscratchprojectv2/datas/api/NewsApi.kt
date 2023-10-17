package com.manuepi.fromscratchprojectv2.datas.api

import com.manuepi.fromscratchprojectv2.common.NetworkResponse
import com.manuepi.fromscratchprojectv2.datas.api.models.NewsApiResponseModel

interface NewsApi {
    // get news from Service
    suspend fun getNews(language: String, word: String): NetworkResponse<NewsApiResponseModel>
}