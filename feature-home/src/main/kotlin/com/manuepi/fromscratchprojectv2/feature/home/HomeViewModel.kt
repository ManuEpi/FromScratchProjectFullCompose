package com.manuepi.fromscratchprojectv2.feature.home

import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavHostController
import com.manuepi.fromscratchprojectv2.domain.NewsUseCase
import com.manuepi.fromscratchprojectv2.domain.model.NewsUseCaseStateModel
import com.manuepi.fromscratchprojectv2.feature.home.mapper.NewsMapperUiModel
import com.manuepi.fromscratchprojectv2.feature.home.model.NewsItemUiModel
import com.manuepi.fromscratchprojectv2.feature.home.model.NewsUiStateModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase, private val newsMapperUiModel: NewsMapperUiModel
) : ViewModel() {
    private val _viewState = MutableStateFlow<NewsUiStateModel.State>(NewsUiStateModel.State.Init)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            newsUseCase.itemsModel.flowOn(Dispatchers.IO).map {
                when (it) {
                    NewsUseCaseStateModel.Failure -> {
                        NewsUiStateModel.State.Failure
                    }
                    NewsUseCaseStateModel.NotSet -> {
                        NewsUiStateModel.State.Loading
                    }
                    is NewsUseCaseStateModel.Success -> {
                        NewsUiStateModel.State.Success(
                            model = newsMapperUiModel.mapUseCaseResponseToUi(
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


    fun getNews() {
        viewModelScope.launch {
            newsUseCase.getNews()
        }
    }

    fun onItemClicked(modelUi: NewsItemUiModel, navController: NavHostController) {
        navController.navigate("sakyt")
        Log.e("title", modelUi.title.toString())
        //navigator.navigate(destination = Screens.Home)
    }
}