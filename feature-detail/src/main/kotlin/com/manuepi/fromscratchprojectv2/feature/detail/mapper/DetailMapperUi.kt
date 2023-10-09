package com.manuepi.fromscratchprojectv2.feature.detail.mapper

import com.manuepi.fromscratchprojectv2.domain.model.NewsItemSourceUseCaseModel
import com.manuepi.fromscratchprojectv2.domain.model.NewsItemUseCaseModel
import com.manuepi.fromscratchprojectv2.feature.detail.model.NewItemSourceUiModel
import com.manuepi.fromscratchprojectv2.feature.detail.model.NewItemUiModel
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

class DetailMapperUi @Inject constructor() {
    fun mapUseCaseItemResponseToUi(item: NewsItemUseCaseModel?): NewItemUiModel? =
        item?.let {
            NewItemUiModel(
                source = item.source?.let { source -> mapItemSource(source = source) },
                author = item.author,
                title = item.title,
                description = item.description,
                url = item.url,
                urlToImage = item.urlToImage,
                publishedAt = item.publishedAt,
                content = item.content
            )
        }

    @VisibleForTesting
    internal fun mapItemSource(source: NewsItemSourceUseCaseModel): NewItemSourceUiModel =
        NewItemSourceUiModel(
            id = source.id, name = source.name
        )
}