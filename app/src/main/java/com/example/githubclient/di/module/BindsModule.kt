package com.example.githubclient.di.module

import com.example.githubclient.network.DownloadRepository
import com.example.githubclient.network.DownloadRepositoryIml
import com.example.githubclient.network.ErrorHandler
import com.example.githubclient.network.ErrorHandlerImpl
import dagger.Binds
import dagger.Module

@Module
interface BindsModule {

    @Binds
    fun bindsErrorHandler(errorHandlerImpl: ErrorHandlerImpl): ErrorHandler

    @Binds
    fun bindsDownloadRepository(downloadRepositoryIml: DownloadRepositoryIml): DownloadRepository
}