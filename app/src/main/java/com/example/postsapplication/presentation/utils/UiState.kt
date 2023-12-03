package com.example.postsapplication.presentation.utils


sealed class UiState {
    sealed class Loading : UiState() {
        object LoadFromDb : Loading()
        object LoadFromNetwork : Loading()
    }

    data class Success(val dataSource: DataSource, val latestData: List<Any>) :
        UiState()

    data class Error(val dataSource: DataSource, val message: String) : UiState()
}

sealed class DataSource(val name: String) {
    object Database : DataSource("Database")
    object Network : DataSource("Network")
}
