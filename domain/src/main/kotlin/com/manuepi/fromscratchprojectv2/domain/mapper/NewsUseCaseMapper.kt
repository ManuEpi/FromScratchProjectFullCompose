package com.manuepi.fromscratchprojectv2.domain.mapper

import com.manuepi.fromscratchprojectv2.datas.models.NewsItemLocalCacheModel
import com.manuepi.fromscratchprojectv2.datas.models.NewsItemSourceLocalCacheModel
import com.manuepi.fromscratchprojectv2.datas.models.NewsLocalCacheModel
import com.manuepi.fromscratchprojectv2.datas.models.NewsLocalStateModel
import com.manuepi.fromscratchprojectv2.domain.model.NewsItemSourceUseCaseModel
import com.manuepi.fromscratchprojectv2.domain.model.NewsItemUseCaseModel
import com.manuepi.fromscratchprojectv2.domain.model.NewsUseCaseModel
import com.manuepi.fromscratchprojectv2.domain.model.NewsUseCaseStateModel
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

class NewsUseCaseMapper @Inject constructor() {
    fun mapNewsLocalToUseCase(news: NewsLocalStateModel): NewsUseCaseStateModel =
        when (news) {
            NewsLocalStateModel.Failure -> {
                NewsUseCaseStateModel.Failure
            }

            is NewsLocalStateModel.Success -> NewsUseCaseStateModel.Success(
                model = mapNewsLocalItemsToUseCase(news.model)
            )
            NewsLocalStateModel.NotSet -> NewsUseCaseStateModel.NotSet
        }

    @VisibleForTesting
    internal fun mapNewsLocalItemsToUseCase(model: NewsLocalCacheModel): NewsUseCaseModel =
        NewsUseCaseModel(
            status = model.status,
            totalResults = model.totalResults,
            articles = model.articles.map { article ->
                NewsItemUseCaseModel(
                    source = article.source?.let { mapItemSource(source = it) },
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
    internal fun mapItemSource(source: NewsItemSourceLocalCacheModel): NewsItemSourceUseCaseModel =
        NewsItemSourceUseCaseModel(
            id = source.id,
            name = source.name
        )

    fun mapNewsItemUseCaseToLocal(model: NewsItemUseCaseModel): NewsItemLocalCacheModel =
        NewsItemLocalCacheModel(
            source = model.source?.let { mapItemSourceToLocal(source = it) },
            author = model.author,
            title = model.title,
            description = model.description,
            url = model.url,
            urlToImage = model.urlToImage,
            publishedAt = model.publishedAt,
            content = model.content
        )

    @VisibleForTesting
    internal fun mapItemSourceToLocal(source: NewsItemSourceUseCaseModel): NewsItemSourceLocalCacheModel =
        NewsItemSourceLocalCacheModel(
            id = source.id,
            name = source.name
        )

    fun mapNewsEntiyToUseCase(model: NewsItemLocalCacheModel?): NewsItemUseCaseModel? =
        model?.let {
            NewsItemUseCaseModel(
                source = it.source?.let { source -> mapItemSourceLocalToUseCase(source = source) },
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content
            )
        }

    @VisibleForTesting
    internal fun mapItemSourceLocalToUseCase(source: NewsItemSourceLocalCacheModel): NewsItemSourceUseCaseModel =
        NewsItemSourceUseCaseModel(
            id = source.id,
            name = source.name
        )
}