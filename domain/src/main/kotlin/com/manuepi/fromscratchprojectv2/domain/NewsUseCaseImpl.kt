package com.manuepi.fromscratchprojectv2.domain

import com.manuepi.fromscratchprojectv2.datas.cache.NewsLocalCache
import com.manuepi.fromscratchprojectv2.domain.mapper.NewsUseCaseMapper
import com.manuepi.fromscratchprojectv2.domain.model.NewsItemUseCaseModel
import com.manuepi.fromscratchprojectv2.domain.model.NewsUseCaseStateModel
import com.manuepi.fromscratchprojectv2.datas.repository.NewsRepository
import com.manuepi.fromscratchprojectv2.datas.repository.mappers.NewsMapperRepositoryModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

@ActivityRetainedScoped
class NewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository,
    private val newsLocalCache: NewsLocalCache,
    private val newsUseCaseMapper: NewsUseCaseMapper,
    private val newsMapperRepositoryModel: NewsMapperRepositoryModel,
) : NewsUseCase {

    /**
     * Flow containing the selected news, it will be listened by NewsDetailViewModel in order to show detail of current news
     */
    override val selectedNews: Flow<NewsItemUseCaseModel?> =
        newsLocalCache.selectedItem.map { newsItem ->
            newsUseCaseMapper.mapNewsEntiyToUseCase(newsItem)
        }

    /**
     * Flow containing the model received from service, it will be listened by viewmodel to define our list of items
     */
    override val itemsModel: Flow<NewsUseCaseStateModel> by lazy {
        newsLocalCache.itemsModel.map { itemsModel ->
            newsUseCaseMapper.mapNewsLocalToUseCase(itemsModel)
        }.distinctUntilChanged().flowOn(Dispatchers.IO)
    }

    override suspend fun getNews() {
        withContext(Dispatchers.IO)
        {
            val language = Locale.getDefault().language
            // We get response from repository
            val news = newsRepository.getNews(language = language)

            // And then we update our local data, that will be listened by [itemsModel]
            newsLocalCache.onItemsLoaded(newsMapperRepositoryModel.mapNewsRepoToLocalCache(repositoryModel = news))
        }
    }

    /**
     * When user selected a news we will call this method to update our local data, [selectedNews] will be triggered and used by our new fragment
     */
    override suspend fun updateSelectedNews(model: NewsItemUseCaseModel) {
        newsLocalCache.onItemSelected(newsUseCaseMapper.mapNewsItemUseCaseToLocal(model = model))
    }
}