package com.manuepi.fromscratchprojectv2.datas.impl.cache

import com.manuepi.fromscratchprojectv2.datas.NewsLocalCache
import com.manuepi.fromscratchprojectv2.datas.models.NewsItemLocalCacheModel
import com.manuepi.fromscratchprojectv2.datas.models.NewsLocalStateModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class NewsLocalCacheImpl @Inject constructor() : NewsLocalCache {
    internal val _selectedItem = MutableStateFlow<NewsItemLocalCacheModel?>(null)
    override val selectedItem = _selectedItem.asStateFlow()

    internal val _itemsModel = MutableStateFlow<NewsLocalStateModel>(NewsLocalStateModel.NotSet)
    override val itemsModel = _itemsModel.asStateFlow()

    /**
     * Updating [_selectedItem] value
     */
    override suspend fun onItemSelected(newItem: NewsItemLocalCacheModel) {
        _selectedItem.value = newItem
    }

    /**
     * Updating [_itemsModel] value
     */
    override suspend fun onItemsLoaded(itemsModel: NewsLocalStateModel) {
        _itemsModel.value = itemsModel
    }
}