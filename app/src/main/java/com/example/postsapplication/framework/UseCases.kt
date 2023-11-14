package com.example.postsapplication.framework

import usecase.AddPost
import usecase.GetAllPost
import usecase.GetPost
import usecase.RemovePost

data class UseCases(
    val addPost: AddPost,
    val getAllPost: GetAllPost,
    val getPost: GetPost,
    val removePost: RemovePost
)