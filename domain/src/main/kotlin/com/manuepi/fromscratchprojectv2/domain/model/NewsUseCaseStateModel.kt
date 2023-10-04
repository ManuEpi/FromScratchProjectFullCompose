package com.manuepi.fromscratchprojectv2.domain.model

sealed class NewsUseCaseStateModel {

    object NotSet: NewsUseCaseStateModel()
    data class Success(
        val model: NewsUseCaseModel
    ) : NewsUseCaseStateModel()

    object Failure : NewsUseCaseStateModel()
}