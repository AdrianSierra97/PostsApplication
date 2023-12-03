package com.example.postsapplication.framework.network

import data.Comment
import data.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JsonPlaceholderApi {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") postId: Int): Response<List<Comment>>

    @POST("posts")
    suspend fun addPost(@Body post: Post): Response<Post>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Response<Post>
}