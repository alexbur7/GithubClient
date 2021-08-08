package com.example.githubclient.network

interface DownloadRepository {

    suspend fun downloadingRepo(userName: String, nameRepo: String): Long
}