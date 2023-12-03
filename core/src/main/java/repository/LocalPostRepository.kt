package repository

import data.Comment
import data.Post

class LocalPostRepository (private val dataSource: LocalPostDataSource){
    suspend fun addPost(post: Post) = dataSource.addPost(post)

    suspend fun getPost(id: Int) = dataSource.getPost(id)

    suspend fun getAllPosts() = dataSource.getPosts()

    suspend fun insertPosts(posts: List<Post>) = dataSource.insertPosts(posts)

    suspend fun insertComments(comments: List<Comment>) = dataSource.insertComments(comments)

    suspend fun getCommentPostEntities(postId: Int) = dataSource.getComments(postId)

}