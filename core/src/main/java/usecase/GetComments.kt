package usecase

import repository.LocalPostRepository

class GetComments(private val localPostRepository: LocalPostRepository) {
    suspend operator fun invoke(postId: Int) = localPostRepository.getCommentPostEntities(postId)
}