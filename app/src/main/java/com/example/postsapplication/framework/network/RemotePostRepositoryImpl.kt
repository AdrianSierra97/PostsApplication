package com.example.postsapplication.framework.network

import com.example.postsapplication.framework.network.NetworkUtility.makeRetrofitRequest

import data.Comment
import repository.RemotePostDataSource
import data.Post

class RemotePostRepositoryImpl : RemotePostDataSource {
    private val retrofitService = RetrofitService.buildService(JsonPlaceholderApi::class.java)
    override suspend fun addPost(post: Post): Post =
        makeRetrofitRequest { retrofitService.addPost(post) }

    override suspend fun getPost(id: Int): Post =
        makeRetrofitRequest { retrofitService.getPost(id) }

    override suspend fun getPosts(): List<Post> =
        makeRetrofitRequest { retrofitService.getPosts() }

    override suspend fun getComments(id: Int): List<Comment> =
        makeRetrofitRequest { retrofitService.getComments(id) }

}