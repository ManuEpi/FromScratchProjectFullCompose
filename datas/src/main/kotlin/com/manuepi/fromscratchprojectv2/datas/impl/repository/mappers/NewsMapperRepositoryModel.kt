package com.manuepi.fromscratchprojectv2.datas.impl.repository.mappers

import com.manuepi.fromscratchprojectv2.datas.impl.api.models.NewsApiResponseModel
import com.manuepi.fromscratchprojectv2.datas.impl.api.models.NewsItemSourceApiResponseModel
import com.manuepi.fromscratchprojectv2.common.NetworkResponse
import com.manuepi.fromscratchprojectv2.datas.models.*
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

class NewsMapperRepositoryModel @Inject constructor() {
    fun mapNewsToRepository(response: NetworkResponse<NewsApiResponseModel>): NewsRepositoryStateResponseModel {
        return when (response) {
            is NetworkResponse.Error -> {
                NewsRepositoryStateResponseModel.Failure
            }

            is NetworkResponse.Success -> {
                NewsRepositoryStateResponseModel.Success(model = mapNewsItemsToRepository(data = response.data))
            }
        }
    }

    @VisibleForTesting
    internal fun mapNewsItemsToRepository(data: NewsApiResponseModel): NewsRepositoryResponseModel =
        NewsRepositoryResponseModel(
            status = data.status,
            totalResults = data.totalResults,
            articles = data.articles.map { article ->
                NewsItemRepositoryResponseModel(
                    source = article.source?.let { mapItemSourceToRepository(source = it) },
                    author = article.author,
                    title = article.title,
                    description = article.description,
                    url = article.url,
                    urlToImage = article.urlToImage,
                    publishedAt = article.publishedAt,
                    content = article.content
                )
            }
        )

    @VisibleForTesting
    internal fun mapItemSourceToRepository(source: NewsItemSourceApiResponseModel): NewsItemSourceRepositoryResponseModel =
        NewsItemSourceRepositoryResponseModel(
            id = source.id,
            name = source.name
        )

    fun mapNewsRepoToLocalCache(repositoryModel: NewsRepositoryStateResponseModel): NewsLocalStateModel {
        return when (repositoryModel) {
            is NewsRepositoryStateResponseModel.Failure -> {
                NewsLocalStateModel.Failure
            }

            is NewsRepositoryStateResponseModel.Success -> {
                NewsLocalStateModel.Success(model = mapNewsItemsRepoToLocalCache(data = repositoryModel.model))
            }
        }
    }

    @VisibleForTesting
    internal fun mapNewsItemsRepoToLocalCache(data: NewsRepositoryResponseModel): NewsLocalCacheModel =
        NewsLocalCacheModel(
            status = data.status,
            totalResults = data.totalResults,
            articles = data.articles.map { article ->
                NewsItemLocalCacheModel(
                    source = article.source?.let { mapItemSourceRepoToLocalCache(source = it) },
                    author = article.author,
                    title = article.title,
                    description = article.description,
                    url = article.url,
                    urlToImage = article.urlToImage,
                    publishedAt = article.publishedAt,
                    content = article.content

                )
            }
        )

    @VisibleForTesting
    internal fun mapItemSourceRepoToLocalCache(source: NewsItemSourceRepositoryResponseModel): NewsItemSourceLocalCacheModel =
        NewsItemSourceLocalCacheModel(
            id = source.id,
            name = source.name
        )
}