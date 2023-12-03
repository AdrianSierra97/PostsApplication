package repository

import data.Comment
import data.Post

interface RemotePostDataSource {
    suspend fun addPost(post: Post): Post

    suspend fun getPost(id: Int): Post

    suspend fun getPosts(): List<Post>

    suspend fun getComments(id: Int): List<Comment>
}