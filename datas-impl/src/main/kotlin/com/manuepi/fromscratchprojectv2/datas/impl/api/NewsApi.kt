package com.manuepi.fromscratchprojectv2.datas.impl.api

import com.manuepi.fromscratchprojectv2.common.NetworkResponse
import com.manuepi.fromscratchprojectv2.datas.impl.api.models.NewsApiResponseModel

interface NewsApi {
    // get news from Service
    suspend fun getNews(language: String): NetworkResponse<NewsApiResponseModel>
}