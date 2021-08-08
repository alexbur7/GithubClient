package com.example.githubclient.utils

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.githubclient.R
import com.example.githubclient.utils.extentions.showToast

class DownloadingReceiver(private val downloadId: Long) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val reference = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        if (downloadId == reference) {
            context?.showToast(R.string.file_download)
        }
    }
}