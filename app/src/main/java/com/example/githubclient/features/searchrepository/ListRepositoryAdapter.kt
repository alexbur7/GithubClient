package com.example.githubclient.features.searchrepository

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.R
import com.example.githubclient.base.BaseHolder
import com.example.githubclient.databinding.ItemGithubUserBinding
import com.example.githubclient.model.GithubUser

class ListRepositoryAdapter(private val downloadClick: (String, String) -> Unit) :
    RecyclerView.Adapter<ListRepositoryAdapter.GithubUserHolder>() {

    val githubUsers = mutableListOf<GithubUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserHolder {
        return GithubUserHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_github_user, parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: GithubUserHolder, position: Int) {
        holder.onBind(githubUsers[position])
    }

    override fun getItemCount() = githubUsers.size

    inner class GithubUserHolder(view: View) : BaseHolder<GithubUser, ItemGithubUserBinding>(view) {

        override val viewBinding: ItemGithubUserBinding = ItemGithubUserBinding.bind(view)

        override fun onBind(data: GithubUser) {
            with(viewBinding) {
                nameRepo.text = data.nameRepo
                downloadButton.setOnClickListener {
                    downloadClick.invoke(data.nameUser, data.nameRepo)
                }
                root.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.urlRepo))
                    it.context.startActivity(intent)
                }
            }
        }

    }

}