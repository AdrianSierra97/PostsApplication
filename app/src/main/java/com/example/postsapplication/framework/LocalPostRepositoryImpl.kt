package com.example.postsapplication.framework

import android.content.Context
import com.example.postsapplication.framework.db.CommentEntity
import com.example.postsapplication.framework.db.DatabaseService
import com.example.postsapplication.framework.db.PostEntity
import data.Comment
import data.Post
import repository.LocalPostDataSource

class LocalPostRepositoryImpl(context: Context) : LocalPostDataSource {
    val postDao = DatabaseService.getDataBaseService(context).postDao()

    override suspend fun addPost(post: Post) = postDao.addPostEntity(PostEntity.fromPost(post))

    override suspend fun getPost(id: Int) = postDao.getPostEntity(id)?.toPost()

    override suspend fun getPosts() = postDao.getAllPostEntities().map { it.toPost() }
    override suspend fun insertPosts(posts: List<Post>) =
        postDao.insertPostEntities(posts.map { PostEntity.fromPost(it) })

    override suspend fun insertComments(comments: List<Comment>) =
        postDao.insertCommentEntities(comments.map { CommentEntity.fromComment(it.postId, it) })

    override suspend fun getComments(postId: Int): List<Comment> =
        postDao.getCommentPostEntities(postId).map { it.toComment() }
}