package com.example.githubclient.model

import com.google.gson.annotations.SerializedName

data class GithubUser(
    @SerializedName("full_name")
    private val fullName: String,
    @SerializedName("name")
    val nameRepo: String,
    @SerializedName("svn_url")
    val urlRepo: String
) {
    val nameUser: String
        get() = fullName.substringBefore("/")
}
