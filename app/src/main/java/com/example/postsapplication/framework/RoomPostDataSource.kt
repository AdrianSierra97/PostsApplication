package com.example.postsapplication.framework

import android.content.Context
import com.example.postsapplication.framework.db.DatabaseService
import com.example.postsapplication.framework.db.PostEntity
import data.Post
import repository.PostDataSource

class RoomPostDataSource(context: Context): PostDataSource {
    val postDao = DatabaseService.getDataBaseService(context).postDao()

    override suspend fun add(post: Post) = postDao.addPostEntity(PostEntity.fromPost(post))

    override suspend fun get(id: Int) = postDao.getPostEntity(id)?.toPost()

    override suspend fun getAll() = postDao.getAllPostEntities().map { it.toPost() }

    override suspend fun remove(post: Post) = postDao.deletePostEntity(PostEntity.fromPost(post))
}