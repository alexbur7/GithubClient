package com.example.githubclient.network

import android.content.Context
import com.example.githubclient.R
import com.example.githubclient.utils.extentions.showToast
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(private val context: Context) : ErrorHandler {

    override fun makeToastByException(e: Exception) {
        when (e) {
            is HttpException -> {
                context.showToast(R.string.failure_data)
            }
            else -> {
                context.showToast(R.string.connection_error)
            }
        }
    }

}