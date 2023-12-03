package com.example.postsapplication.framework

import com.example.postsapplication.framework.usecase.AddPost
import com.example.postsapplication.framework.usecase.GetComments
import com.example.postsapplication.framework.usecase.GetPost
import com.example.postsapplication.framework.usecase.GetPosts

data class RemoteUseCases(
    val addPost: AddPost,
    val getPosts: GetPosts,
    val getPost: GetPost,
    val getComments: GetComments
)