package com.manuepi.fromscratchprojectv2.datas.repository

import com.manuepi.fromscratchprojectv2.datas.repository.models.NewsRepositoryStateResponseModel

interface NewsRepository {
    suspend fun getNews(language: String): NewsRepositoryStateResponseModel
}