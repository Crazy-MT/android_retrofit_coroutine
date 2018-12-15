package com.example.retrofitandcoroutine.api


import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users/{username}")
    fun getGithubAccount(@Path("username") username: String): Deferred<Response<GithubAccount>>
}