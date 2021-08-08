package com.example.githubclient.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.githubclient.R
import com.example.githubclient.databinding.ActivityMainBinding
import com.example.githubclient.features.searchrepository.SearchRepositoryFragment
import com.example.githubclient.network.GithubApi
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _viewBinding: ActivityMainBinding? = null
    private val viewBinding by lazy { _viewBinding!! }

    @Inject
    lateinit var githubApi: GithubApi

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        _viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container,
            SearchRepositoryFragment()
        ).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}