package com.example.postsapplication.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPostEntity(postEntity: PostEntity)

    @Query("SELECT * FROM post WHERE id = :id")
    suspend fun getPostEntity(id: Int): PostEntity?

    @Query("SELECT * FROM post")
    suspend fun getAllPostEntities(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostEntities(posts: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommentEntities(posts: List<CommentEntity>)

    @Query("SELECT * FROM comment WHERE postId = :postId")
    suspend fun getCommentPostEntities(postId: Int): List<CommentEntity>
}