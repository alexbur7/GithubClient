package com.example.githubclient.di.module

import com.example.githubclient.di.AppScope
import com.example.githubclient.network.GithubApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesBaseUrl(): String {
        return "https://api.github.com/"
    }

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun providesRetrofit(gson: Gson, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl).build()
    }


    @AppScope
    @Provides
    fun providesGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

}