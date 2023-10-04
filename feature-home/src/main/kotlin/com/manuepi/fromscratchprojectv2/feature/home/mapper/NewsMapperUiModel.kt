package com.manuepi.fromscratchprojectv2.feature.home.mapper

import com.manuepi.fromscratchprojectv2.domain.model.NewsItemSourceUseCaseModel
import com.manuepi.fromscratchprojectv2.domain.model.NewsItemUseCaseModel
import com.manuepi.fromscratchprojectv2.domain.model.NewsUseCaseModel
import com.manuepi.fromscratchprojectv2.feature.home.model.NewsItemSourceUiModel
import com.manuepi.fromscratchprojectv2.feature.home.model.NewsItemUiModel
import com.manuepi.fromscratchprojectv2.feature.home.model.NewsUiModel
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

class NewsMapperUiModel @Inject constructor() {
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

    fun mapUiToUseCase(uiModel: NewsItemUiModel): NewsItemUseCaseModel = NewsItemUseCaseModel(
        source = uiModel.source?.let { mapItemSourceToUseCase(source = it) },
        author = uiModel.author,
        title = uiModel.title,
        description = uiModel.description,
        url = uiModel.url,
        urlToImage = uiModel.urlToImage,
        publishedAt = uiModel.publishedAt,
        content = uiModel.content
    )

    @VisibleForTesting
    internal fun mapItemSourceToUseCase(source: NewsItemSourceUiModel): NewsItemSourceUseCaseModel =
        NewsItemSourceUseCaseModel(
            id = source.id, name = source.name
        )
}