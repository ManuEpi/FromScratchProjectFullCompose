package com.manuepi.fromscratchprojectv2.feature.detail.model

data class NewItemUiModel(
    val source: NewItemSourceUiModel?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

data class NewItemSourceUiModel(
    val id: String?,
    val name: String?
)