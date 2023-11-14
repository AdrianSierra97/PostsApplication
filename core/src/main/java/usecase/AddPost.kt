package usecase

import data.Post
import repository.PostRepository

class AddPost(private val postRepository: PostRepository) {
    suspend operator fun invoke(post: Post) = postRepository.addPost(post)
}