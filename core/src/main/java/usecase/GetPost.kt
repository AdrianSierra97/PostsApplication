package usecase

import repository.LocalPostRepository


class GetPost(private val localPostRepository: LocalPostRepository){
    suspend operator fun invoke(id: Int) = localPostRepository.getPost(id)
}