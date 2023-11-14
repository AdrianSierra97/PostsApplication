package repository

import data.Post

interface PostDataSource {
    suspend fun add(post: Post)

    suspend fun get(id: Int): Post?

    suspend fun getAll(): List<Post>

    suspend fun remove(post: Post)
}