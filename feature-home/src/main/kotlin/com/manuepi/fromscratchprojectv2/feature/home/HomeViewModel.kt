package com.manuepi.fromscratchprojectv2.feature.home

import androidx.lifecycle.*
import com.manuepi.fromscratchprojectv2.domain.NewsUseCase
import com.manuepi.fromscratchprojectv2.domain.model.NewsUseCaseStateModel
import com.manuepi.fromscratchprojectv2.feature.home.mapper.HomeMapperUi
import com.manuepi.fromscratchprojectv2.feature.home.model.NewsItemUiModel
import com.manuepi.fromscratchprojectv2.feature.home.model.HomeUiStateModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase, private val homeMapperUi: HomeMapperUi
) : ViewModel() {
    private val _viewState = MutableStateFlow<HomeUiStateModel.State>(HomeUiStateModel.State.Init)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            newsUseCase.itemsModel.flowOn(Dispatchers.IO).map {
                when (it) {
                    NewsUseCaseStateModel.Failure -> {
                        HomeUiStateModel.State.Failure
                    }
                    NewsUseCaseStateModel.NotSet -> {
                        HomeUiStateModel.State.Loading
                    }
                    is NewsUseCaseStateModel.Success -> {
                        HomeUiStateModel.State.Success(
                            model = homeMapperUi.mapUseCaseResponseToUi(
                                response = it.model
                            )
                        )
                    }
                }
            }.collect { newState ->
                _viewState.update {
                    newState
                }
            }
        }
    }

    fun updateSelectedNews(modelUi: NewsItemUiModel) {
        viewModelScope.launch {
            newsUseCase.updateSelectedNews(
                model = homeMapperUi.mapUiToUseCase(modelUi = modelUi)
            )
        }
    }

    fun getNews() {
        viewModelScope.launch {
            newsUseCase.getNews()
        }
    }
}