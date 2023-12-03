package com.example.postsapplication.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import data.Comment

@Entity(tableName = "comment")
class CommentEntity(
    val postId: Int,
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val body: String
){
    companion object {
        fun fromComment(postId: Int, comment: Comment) =
            CommentEntity(
                postId = postId,
                id = comment.id,
                name = comment.name,
                email = comment.email,
                body = comment.body
            )
    }

    fun toComment() = Comment(
        postId,
        id,
        name,
        email,
        body
    )
}