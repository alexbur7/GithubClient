package com.example.githubclient.features.downloadlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.db.DownloadRepoDao
import com.example.githubclient.model.DownloadRepoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DownloadRepositoryViewModel @Inject constructor(private val dao: DownloadRepoDao) :
    ViewModel() {

    private val _downloadRepos = MutableStateFlow(emptyList<DownloadRepoEntity>())
    val downloadRepos: StateFlow<List<DownloadRepoEntity>> = _downloadRepos.asStateFlow()
    private val _stateFlow = MutableStateFlow(State.START_DOWNLOADING)
    val stateFlow: StateFlow<State> = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _stateFlow.value = State.START_DOWNLOADING
            _downloadRepos.value = dao.getAllDownloadRepo()
            _stateFlow.value = State.END_DOWNLOADING
        }
    }

    enum class State {
        START_DOWNLOADING, END_DOWNLOADING
    }

}