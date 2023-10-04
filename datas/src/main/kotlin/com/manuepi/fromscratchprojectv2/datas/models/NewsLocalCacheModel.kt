package com.manuepi.fromscratchprojectv2.datas.models

data class NewsLocalCacheModel(
    val status: String?,
    val totalResults: Int?,
    val articles: List<NewsItemLocalCacheModel>
)
data class NewsItemLocalCacheModel(
    val source: NewsItemSourceLocalCacheModel?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

data class NewsItemSourceLocalCacheModel(
    val id: String?,
    val name: String?
)