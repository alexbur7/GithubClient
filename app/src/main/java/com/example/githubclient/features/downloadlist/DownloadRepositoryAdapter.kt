package com.example.githubclient.features.downloadlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.R
import com.example.githubclient.base.BaseHolder
import com.example.githubclient.databinding.ItemDownloadRepoBinding
import com.example.githubclient.model.DownloadRepoEntity

class DownloadRepositoryAdapter :
    RecyclerView.Adapter<DownloadRepositoryAdapter.DownloadRepositoryHolder>() {

    val downloadRepos = mutableListOf<DownloadRepoEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadRepositoryHolder {
        return DownloadRepositoryHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_download_repo, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DownloadRepositoryHolder, position: Int) {
        holder.onBind(downloadRepos[position])
    }

    override fun getItemCount() = downloadRepos.size

    inner class DownloadRepositoryHolder(view: View) :
        BaseHolder<DownloadRepoEntity, ItemDownloadRepoBinding>(view) {
        override val viewBinding: ItemDownloadRepoBinding = ItemDownloadRepoBinding.bind(view)

        override fun onBind(data: DownloadRepoEntity) {
            with(viewBinding) {
                userName.text = data.userName
                nameRepo.text = data.nameRepo
            }
        }

    }

}