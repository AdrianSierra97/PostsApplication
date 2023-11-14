package com.example.postsapplication.framework.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import data.Post
import kotlinx.coroutines.launch

class PostListViewModel(application: Application) : BaseAndroidViewModel(application) {

    val posts = MutableLiveData<List<Post>>()

    fun getAllPosts() {
        coroutineScope.launch {
            val postList = useCases.getAllPost()
            posts.postValue(postList)
        }
    }
}