package com.manuepi.fromscratchprojectv2.domain

import com.manuepi.fromscratchprojectv2.domain.model.NewsItemUseCaseModel
import com.manuepi.fromscratchprojectv2.domain.model.NewsUseCaseStateModel
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {

    val selectedNews: Flow<NewsItemUseCaseModel?>
    val itemsModel: Flow<NewsUseCaseStateModel>

    suspend fun getNews(word: String)
    suspend fun updateSelectedNews(model: NewsItemUseCaseModel)
}