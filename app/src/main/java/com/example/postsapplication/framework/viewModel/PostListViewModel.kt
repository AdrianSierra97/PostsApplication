package com.example.postsapplication.framework.viewModel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.postsapplication.presentation.utils.DataSource
import com.example.postsapplication.presentation.utils.UiState
import data.Post
import kotlinx.coroutines.launch

class PostListViewModel(application: Application) : BaseAndroidViewModel(application) {

    private var _currentPosts: List<Post> = emptyList()
    private val currentPosts: List<Post>
        get() = _currentPosts


    fun loadData() {
        _uiState.value = UiState.Loading.LoadFromDb

        viewModelScope.launch {
            _uiState.value = UiState.Loading.LoadFromNetwork

            try {
                val remotePosts = remoteUseCases.getPosts()


                _uiState.value = UiState.Success(
                    DataSource.Network,
                    remotePosts
                )

                val newPosts = remotePosts.filterNot { remotePost ->
                    currentPosts.any { currentPost ->
                        remotePost.id == currentPost.id && remotePost.title == currentPost.title && remotePost.body == currentPost.body
                    }
                }
                newPosts.forEach {
                    it.updateTime = System.currentTimeMillis()
                }

                useCases.insertPost(newPosts)
            } catch (exception: Exception) {
                _uiState.value = UiState.Error(DataSource.Network, "Network Request failed")
            }

            _currentPosts = useCases.getPosts()

            if (currentPosts.isNotEmpty()) {
                _uiState.value = UiState.Success(DataSource.Database, currentPosts)
            } else {
                _uiState.value =
                    UiState.Error(DataSource.Database, "Database empty!")
            }


        }
    }
}