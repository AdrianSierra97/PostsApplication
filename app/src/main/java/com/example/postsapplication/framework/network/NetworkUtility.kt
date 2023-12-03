package com.example.postsapplication.framework.network

import retrofit2.Response

object NetworkUtility  {
    inline fun <reified T> makeRetrofitRequest(call: () -> Response<T>): T {
        val response = call()
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Response body is null")
        } else {
            throw Exception("Network call failed: ${response.errorBody()?.string()}")
        }
    }
}