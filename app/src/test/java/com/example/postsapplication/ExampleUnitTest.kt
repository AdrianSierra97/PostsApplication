package com.example.postsapplication

import com.example.postsapplication.framework.network.JsonPlaceholderApi
import com.example.postsapplication.framework.network.RetrofitService
import data.Comment
import data.Post
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response


class ExampleUnitTest {

        @Test
        fun testGetPosts() {
            val mock = mock(RetrofitService::class.java)
            `when`(mock.buildService(JsonPlaceholderApi::class.java)).thenReturn(
                object : JsonPlaceholderApi {
                    override suspend fun getPosts(): Response<List<Post>> {
                        return Response.success(
                            listOf(
                                Post(
                                    1,
                                    1,
                                    "title",
                                    "body",
                                    0
                                )
                            )
                        )
                    }

                    override suspend fun getComments(postId: Int): Response<List<Comment>> {
                        return Response.success(
                            listOf(
                                Comment(
                                    1,
                                    1,
                                    "name",
                                    "email",
                                    "body"
                                )
                            )
                        )
                    }

                    override suspend fun addPost(post: Post): Response<Post> {
                        return Response.success(post)
                    }

                    override suspend fun getPost(id: Int): Response<Post> {
                        return Response.success(
                            Post(
                                1,
                                1,
                                "title",
                                "body",
                                0
                            )
                        )
                    }
                }
            )
        }
    }
