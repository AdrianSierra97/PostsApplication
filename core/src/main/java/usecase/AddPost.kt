package usecase

import data.Post
import repository.LocalPostRepository

class AddPost(private val localPostRepository: LocalPostRepository) {
    suspend operator fun invoke(post: Post) = localPostRepository.addPost(post)
}