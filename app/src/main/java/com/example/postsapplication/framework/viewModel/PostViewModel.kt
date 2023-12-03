package com.example.postsapplication.framework.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.postsapplication.presentation.utils.DataSource
import com.example.postsapplication.presentation.utils.UiState
import data.Comment
import data.Post
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : BaseAndroidViewModel(application) {


    var postId: Int = 0
    val saved = MutableLiveData<Boolean>()
    private val _currentPost = MutableLiveData<Post?>()
    val currentPost: LiveData<Post?>
        get() = _currentPost


    fun savePost(post: Post) {
        viewModelScope.launch {
            try {
                remoteUseCases.addPost(post)
            } catch (exception: Exception) {
                _uiState.value =
                    UiState.Error(DataSource.Network, "Save Post remote failed")
            }
            try {
                useCases.addPost(post)
            } catch (exception: Exception) {
                _uiState.value =
                    UiState.Error(DataSource.Database, "Save Post local failed")
            }
            saved.value = true
        }
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading.LoadFromNetwork
            var remoteComments = listOf<Comment>()
            try {
                remoteComments = remoteUseCases.getComments(postId)
                _uiState.value = remoteComments.let {
                    UiState.Success(
                        DataSource.Network,
                        it
                    )
                }
            } catch (exception: Exception) {
                _uiState.value = UiState.Error(DataSource.Network, "Remote comments load failed!")
            }

            _uiState.value = UiState.Loading.LoadFromDb

            saveComments(remoteComments)

            try {
                val comments = postId.let { useCases.getComments(it) }
                if (comments.isNotEmpty()) {
                    _uiState.value = comments.let { UiState.Success(DataSource.Database, it) }
                } else {
                    _uiState.value =
                        UiState.Error(DataSource.Database, "Database empty!")
                }
            } catch (exception: Exception) {
                _uiState.value = UiState.Error(DataSource.Database, "Database get comments failed!")
            }
        }
    }

    private suspend fun saveComments(remoteComments: List<Comment>?) {
            try {
                remoteComments?.let { useCases.insertComments(it) }
            } catch (exception: Exception) {
                _uiState.value =
                    UiState.Error(DataSource.Database, "Save comments local failed")
            }
    }

    fun getCurrentPost() {
        viewModelScope.launch {
            _currentPost.postValue(postId.let {
                useCases.getPost(it)
            })
        }
    }
}