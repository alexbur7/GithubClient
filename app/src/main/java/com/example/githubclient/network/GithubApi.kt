package com.example.githubclient.network

import com.example.githubclient.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{name}/repos")
    suspend fun getRepositoryUser(@Path("name") nameUser: String): List<GithubUser>

}