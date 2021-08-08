package com.example.githubclient.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseHolder<T, VB : ViewBinding>(view: View) : RecyclerView.ViewHolder(view) {

    abstract val viewBinding: VB

    abstract fun onBind(data: T)
}