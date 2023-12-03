package com.example.core

import usecase.AddPost
import usecase.GetComments
import usecase.GetPosts
import usecase.GetPost
import usecase.InsertComments
import usecase.InsertPosts

data class UseCases(
    val addPost: AddPost,
    val getPosts: GetPosts,
    val getPost: GetPost,
    val insertPost: InsertPosts,
    val insertComments: InsertComments,
    val getComments: GetComments
)