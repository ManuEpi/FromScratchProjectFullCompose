package com.manuepi.fromscratchprojectv2.datas.repository.models

sealed class NewsRepositoryStateResponseModel {
    data class Success(
        val model: NewsRepositoryResponseModel
    ) : NewsRepositoryStateResponseModel()

    object Failure : NewsRepositoryStateResponseModel()
}