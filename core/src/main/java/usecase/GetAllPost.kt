package usecase

import repository.PostRepository

class GetAllPost(private val postRepository: PostRepository) {
    suspend operator fun invoke() = postRepository.getAllPosts()
}