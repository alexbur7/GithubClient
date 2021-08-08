package com.example.githubclient.network

interface ErrorHandler {
    fun makeToastByException(e: Exception)
}