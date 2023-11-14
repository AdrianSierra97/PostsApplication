package usecase

import data.Post
import repository.PostRepository

class RemovePost(private val postRepository: PostRepository) {
    suspend operator fun invoke(post: Post) = postRepository.removePost(post)
}