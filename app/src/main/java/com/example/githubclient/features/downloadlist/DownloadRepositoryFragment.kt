package com.example.githubclient.features.downloadlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient.base.BaseFragment
import com.example.githubclient.databinding.FragmentDownloadingRepositoryBinding
import com.example.githubclient.utils.ViewModelFactory
import com.example.githubclient.utils.extentions.gone
import com.example.githubclient.utils.extentions.show
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DownloadRepositoryFragment : BaseFragment<FragmentDownloadingRepositoryBinding>() {
    override val bindingInflater: (inflater: LayoutInflater, viewGroup: ViewGroup?, attach: Boolean)
    -> FragmentDownloadingRepositoryBinding =
        { inflater, viewGroup, attach ->
            FragmentDownloadingRepositoryBinding.inflate(inflater, viewGroup, attach)
        }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DownloadRepositoryViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun settingView() {
        viewModel = viewModelFactory.create(DownloadRepositoryViewModel::class.java)
        with(viewBinding) {
            val downloadAdapter = DownloadRepositoryAdapter()
            listRepository.run {
                adapter = downloadAdapter
                layoutManager = LinearLayoutManager(context)
            }

            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.downloadRepos.collect { repos ->
                        downloadAdapter.run {
                            downloadRepos.clear()
                            downloadRepos.addAll(repos)
                            notifyDataSetChanged()
                        }
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.stateFlow.collect { state ->
                        when (state) {
                            DownloadRepositoryViewModel.State.START_DOWNLOADING ->
                                viewBinding.progressBar.show()
                            DownloadRepositoryViewModel.State.END_DOWNLOADING ->
                                viewBinding.progressBar.gone()
                        }
                    }
                }
            }
        }
    }
}