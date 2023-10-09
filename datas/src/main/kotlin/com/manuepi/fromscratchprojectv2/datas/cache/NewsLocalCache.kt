package com.manuepi.fromscratchprojectv2.datas.cache

import com.manuepi.fromscratchprojectv2.datas.cache.models.NewsItemLocalCacheModel
import com.manuepi.fromscratchprojectv2.datas.cache.models.NewsLocalStateModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface NewsLocalCache {

    /**
     * Flow containing the selected item
     */
    val selectedItem: Flow<NewsItemLocalCacheModel?>

    /**
     * Flow containing the list of items and infos
     */
    val itemsModel: StateFlow<NewsLocalStateModel>

    suspend fun onItemSelected(newItem: NewsItemLocalCacheModel)
    suspend fun onItemsLoaded(itemsModel: NewsLocalStateModel)
}