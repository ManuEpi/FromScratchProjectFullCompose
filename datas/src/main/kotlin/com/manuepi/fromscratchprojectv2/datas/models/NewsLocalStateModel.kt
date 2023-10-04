package com.manuepi.fromscratchprojectv2.datas.models

sealed class NewsLocalStateModel {
    object NotSet : NewsLocalStateModel()

    data class Success(
        val model: NewsLocalCacheModel
    ) : NewsLocalStateModel()

    object Failure : NewsLocalStateModel()
}