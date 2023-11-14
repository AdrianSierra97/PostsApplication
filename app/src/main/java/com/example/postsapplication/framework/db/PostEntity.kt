package com.example.postsapplication.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import data.Post

@Entity(tableName = "post")
data class PostEntity(
    val userId: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val body: String,
    val creationTime: Long,
    val updateTime: Long,
    val isUploaded: Boolean = false
) {
    companion object {
        fun fromPost(post: Post) =
            PostEntity(
                userId = post.userId,
                id = post.id,
                title = post.title,
                body = post.body,
                creationTime = post.creationTime,
                updateTime = post.updateTime,
                isUploaded = post.isUploaded
            )
    }

    fun toPost() = Post(
        userId,
        id,
        title,
        body,
        creationTime,
        updateTime,
        isUploaded
    )
}
