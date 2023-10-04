package com.manuepi.fromscratchprojectv2.feature.home.model

data class NewsUiStateModel(
    val state: State = State.Init
) {
    sealed class State {
        object Init : State()
        object Loading : State()
        data class Success(
            val model: NewsUiModel
        ) : State()

        object Failure : State()
    }
}