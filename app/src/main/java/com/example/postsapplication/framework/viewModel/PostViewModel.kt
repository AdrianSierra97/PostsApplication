package com.example.postsapplication.framework.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import data.Post
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : BaseAndroidViewModel(application) {

    val saved = MutableLiveData<Boolean>()
    val currentPost = MutableLiveData<Post?>()

    fun savePost(post: Post) {
        coroutineScope.launch {
            useCases.addPost(post)
            saved.postValue(true)
        }
    }

    fun getPost(id: Int){
        coroutineScope.launch{
           val post = useCases.getPost(id)
            currentPost.postValue(post)
        }
    }
}