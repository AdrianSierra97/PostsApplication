package com.example.postsapplication.framework

import data.Post
import repository.RemotePostDataSource

class RemotePostRepository (private val dataSource: RemotePostDataSource) {
    suspend fun addPost(post: Post) = dataSource.addPost(post)

    suspend fun getPost(id: Int) = dataSource.getPost(id)

    suspend fun getPosts() = dataSource.getPosts()

    suspend fun getComments(postId: Int) = dataSource.getComments(postId)
}