package usecase

import data.Post
import repository.LocalPostRepository

class InsertPosts(private val localPostRepository: LocalPostRepository) {
    suspend operator fun invoke(posts: List<Post>) = localPostRepository.insertPosts(posts)
}