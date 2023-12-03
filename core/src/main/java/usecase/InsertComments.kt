package usecase

import data.Comment
import repository.LocalPostRepository

class InsertComments(private val localPostRepository: LocalPostRepository) {
    suspend operator fun invoke(comments: List<Comment>) = localPostRepository.insertComments(comments)
}