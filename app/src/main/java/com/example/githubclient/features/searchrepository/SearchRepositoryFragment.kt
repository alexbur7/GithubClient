package com.example.githubclient.features.searchrepository

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.IntentFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient.base.BaseFragment
import com.example.githubclient.databinding.FragmentSearchRepositoryBinding
import com.example.githubclient.utils.extentions.gone
import com.example.githubclient.utils.extentions.show
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.githubclient.R
import com.example.githubclient.features.downloadlist.DownloadRepositoryFragment
import com.example.githubclient.utils.DownloadingReceiver
import com.example.githubclient.utils.ViewModelFactory
import com.example.githubclient.utils.extentions.showToast

class SearchRepositoryFragment :
    BaseFragment<FragmentSearchRepositoryBinding>() {

    override val bindingInflater: (
        inflater: LayoutInflater, viewGroup: ViewGroup?,
        attach: Boolean
    ) -> FragmentSearchRepositoryBinding =
        { inflater, viewGroup, attach ->
            FragmentSearchRepositoryBinding.inflate(inflater, viewGroup, attach)
        }

    private companion object {
        const val REQUIRED_PERMISSIONS = "android.permission.WRITE_EXTERNAL_STORAGE"
    }

    private fun allPermissionsGranted(): Boolean {
        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    REQUIRED_PERMISSIONS
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return true
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SearchRepositoryViewModel

    private val checkPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            context?.showToast(
                if (it) {
                    R.string.restart_download
                } else {
                    R.string.need_permission
                }
            )

        }

    @SuppressLint("NotifyDataSetChanged")
    override fun settingView() {
        viewModel = viewModelFactory.create(SearchRepositoryViewModel::class.java)
        val repositoryAdapter = ListRepositoryAdapter(::downloadRepo)
        with(viewBinding) {
            listRepository.run {
                adapter = repositoryAdapter
                layoutManager = LinearLayoutManager(this.context)
            }

            searchButton.setOnClickListener {
                viewModel.searchRepository(searchText.text.toString().trim())
            }

            nextFragment.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, DownloadRepositoryFragment())
                    .addToBackStack(null)
                    .commit()
            }

            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.stateFlow.collect {
                        when (it) {
                            SearchRepositoryViewModel.DownloadState.END_DOWNLOADING ->
                                progressBar.gone()
                            SearchRepositoryViewModel.DownloadState.START_DOWNLOADING -> {
                                progressBar.show()
                            }
                            SearchRepositoryViewModel.DownloadState.ERROR -> {
                                progressBar.gone()
                            }
                            SearchRepositoryViewModel.DownloadState.START_DOWNLOADING_FILE -> {
                                root.context.showToast(R.string.start_downloading_file)
                            }
                        }
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.listRepositoryFlow.collect { users ->
                        repositoryAdapter.run {
                            githubUsers.clear()
                            githubUsers.addAll(users)
                            notifyDataSetChanged()
                        }
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.downloadIdFlow.collect { id ->
                        root.context.registerReceiver(
                            DownloadingReceiver(id),
                            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
                        )
                    }
                }
            }
        }
    }

    private fun downloadRepo(userName: String, nameRepo: String) {
        if (allPermissionsGranted()) {
            viewModel.downloadRepo(userName, nameRepo)
        } else {
            checkPermission.launch(REQUIRED_PERMISSIONS)
        }
    }

}

