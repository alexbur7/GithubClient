package com.example.githubclient.di.module

import com.example.githubclient.base.MainActivity
import com.example.githubclient.di.FragmentScope
import com.example.githubclient.features.downloadlist.DownloadRepositoryFragment
import com.example.githubclient.features.searchrepository.SearchRepositoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector
    fun provideMainActivityFactory(): MainActivity

    @FragmentScope
    @ContributesAndroidInjector
    fun provideSearchRepositoryFragment(): SearchRepositoryFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun provideDownloadRepositoryFragment(): DownloadRepositoryFragment
}