package com.example.githubclient.di.module

import androidx.lifecycle.ViewModel
import com.example.githubclient.di.ViewModelKey
import com.example.githubclient.features.downloadlist.DownloadRepositoryViewModel
import com.example.githubclient.features.searchrepository.SearchRepositoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @[IntoMap ViewModelKey(SearchRepositoryViewModel::class)]
    fun provideSearchRepositoryViewModel(searchViewModel: SearchRepositoryViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(DownloadRepositoryViewModel::class)]
    fun provideDownloadRepository(downloadRepos: DownloadRepositoryViewModel): ViewModel


}