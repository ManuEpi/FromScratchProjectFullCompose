package com.manuepi.fromscratchprojectv2.datas

import com.manuepi.fromscratchprojectv2.datas.models.NewsRepositoryStateResponseModel

interface NewsRepository {
    suspend fun getNews(language: String): NewsRepositoryStateResponseModel
}