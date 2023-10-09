package com.manuepi.fromscratchprojectv2.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuepi.fromscratchprojectv2.domain.NewsUseCase
import com.manuepi.fromscratchprojectv2.feature.detail.mapper.DetailMapperUi
import com.manuepi.fromscratchprojectv2.feature.detail.model.NewItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase, private val detailMapperUi: DetailMapperUi
) : ViewModel() {
    private val _viewState = MutableStateFlow<NewItemUiModel?>(null)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            newsUseCase.selectedNews.flowOn(Dispatchers.IO).collect {
                val mappedResult = detailMapperUi.mapUseCaseItemResponseToUi(item = it)
                _viewState.update {
                    mappedResult
                }
            }
        }
    }
}