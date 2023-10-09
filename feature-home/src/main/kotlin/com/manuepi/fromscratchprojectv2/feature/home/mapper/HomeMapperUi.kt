package com.manuepi.fromscratchprojectv2.feature.home.mapper

import com.manuepi.fromscratchprojectv2.domain.model.NewsItemSourceUseCaseModel
import com.manuepi.fromscratchprojectv2.domain.model.NewsItemUseCaseModel
import com.manuepi.fromscratchprojectv2.domain.model.NewsUseCaseModel
import com.manuepi.fromscratchprojectv2.feature.home.model.NewsItemSourceUiModel
import com.manuepi.fromscratchprojectv2.feature.home.model.NewsItemUiModel
import com.manuepi.fromscratchprojectv2.feature.home.model.NewsUiModel
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

class HomeMapperUi @Inject constructor() {
    fun mapUseCaseResponseToUi(response: NewsUseCaseModel): NewsUiModel =
        NewsUiModel(totalResults = response.totalResults,
            articles = response.articles.map { article ->
                mapUseCaseItemResponseToUi(article)
            })

    fun mapUseCaseItemResponseToUi(item: NewsItemUseCaseModel): NewsItemUiModel =
        NewsItemUiModel(
            source = item.source?.let { source -> mapItemSource(source = source) },
            author = item.author,
            title = item.title,
            description = item.description,
            url = item.url,
            urlToImage = item.urlToImage,
            publishedAt = item.publishedAt,
            content = item.content
        )

    @VisibleForTesting
    internal fun mapItemSource(source: NewsItemSourceUseCaseModel): NewsItemSourceUiModel =
        NewsItemSourceUiModel(
            id = source.id, name = source.name
        )

    fun mapUiToUseCase(modelUi: NewsItemUiModel): NewsItemUseCaseModel = NewsItemUseCaseModel(
        source = modelUi.source?.let { mapItemSourceToUseCase(source = it) },
        author = modelUi.author,
        title = modelUi.title,
        description = modelUi.description,
        url = modelUi.url,
        urlToImage = modelUi.urlToImage,
        publishedAt = modelUi.publishedAt,
        content = modelUi.content
    )

    @VisibleForTesting
    internal fun mapItemSourceToUseCase(source: NewsItemSourceUiModel): NewsItemSourceUseCaseModel =
        NewsItemSourceUseCaseModel(
            id = source.id, name = source.name
        )
}