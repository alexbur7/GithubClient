package com.example.githubclient.features.searchrepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.model.GithubUser
import com.example.githubclient.network.DownloadRepository
import com.example.githubclient.network.ErrorHandler
import com.example.githubclient.network.GithubApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class SearchRepositoryViewModel @Inject constructor(
    private val githubApi: GithubApi,
    private val downloadRepository: DownloadRepository,
    private val errorHandler: ErrorHandler
) :
    ViewModel() {

    private val _stateFlow = MutableStateFlow(DownloadState.END_DOWNLOADING)
    val stateFlow: StateFlow<DownloadState> = _stateFlow.asStateFlow()
    private val _listRepositoryFlow = MutableStateFlow(emptyList<GithubUser>())
    val listRepositoryFlow: StateFlow<List<GithubUser>> = _listRepositoryFlow.asStateFlow()
    private val _downloadIdFlow = MutableStateFlow(-1L)
    val downloadIdFlow: StateFlow<Long> = _downloadIdFlow.asStateFlow()

    fun searchRepository(nameUser: String) = viewModelScope.launch {
        _stateFlow.value = DownloadState.START_DOWNLOADING
        try {
            _listRepositoryFlow.value = githubApi.getRepositoryUser(nameUser)
            _stateFlow.value = DownloadState.END_DOWNLOADING
        } catch (e: Exception) {
            errorHandler.makeToastByException(e)
            _stateFlow.value = DownloadState.ERROR
        }
    }

    fun downloadRepo(nameUser: String, nameRepo: String) = viewModelScope.launch {
        _stateFlow.value = DownloadState.START_DOWNLOADING_FILE
        _downloadIdFlow.value = downloadRepository.downloadingRepo(nameUser, nameRepo)
    }

    enum class DownloadState {
        END_DOWNLOADING, START_DOWNLOADING, ERROR, START_DOWNLOADING_FILE
    }
}