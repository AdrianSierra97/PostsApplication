package usecase

import repository.PostRepository


class GetPost(private val postRepository: PostRepository){
    suspend operator fun invoke(id: Int) = postRepository.getPost(id)
}