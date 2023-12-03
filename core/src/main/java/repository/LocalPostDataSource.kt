package repository

import data.Comment
import data.Post

interface LocalPostDataSource {
    suspend fun addPost(post: Post)

    suspend fun getPost(id: Int): Post?

    suspend fun getPosts(): List<Post>

    suspend fun insertPosts(posts: List<Post>)

    suspend fun insertComments(comments: List<Comment>)

    suspend fun getComments(postId: Int): List<Comment>
}