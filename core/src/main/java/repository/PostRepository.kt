package repository

import data.Post

class PostRepository (private val dataSource: PostDataSource){
    suspend fun addPost(post: Post) = dataSource.add(post)

    suspend fun getPost(id: Int) = dataSource.get(id)

    suspend fun getAllPosts() = dataSource.getAll()

    suspend fun removePost(post: Post) = dataSource.remove(post)
}