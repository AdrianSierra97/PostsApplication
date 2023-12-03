package usecase

import repository.LocalPostRepository

class GetPosts(private val localPostRepository: LocalPostRepository) {
    suspend operator fun invoke() = localPostRepository.getAllPosts()
}