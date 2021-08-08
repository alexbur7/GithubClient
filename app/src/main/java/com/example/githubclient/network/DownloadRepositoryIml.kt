package com.example.githubclient.network

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import com.example.githubclient.db.DownloadRepoDao
import com.example.githubclient.model.DownloadRepoEntity
import javax.inject.Inject

class DownloadRepositoryIml @Inject constructor(
    private val baseUrl: String,
    private val downloadManager: DownloadManager,
    private val dao: DownloadRepoDao,
) : DownloadRepository {

    override suspend fun downloadingRepo(userName: String, nameRepo: String): Long {
        val url = "${baseUrl}repos/${userName}/${nameRepo}/zipball"
        val request = DownloadManager
            .Request(Uri.parse(url)).apply {
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    "${nameRepo}.zip"
                )
                setAllowedOverRoaming(false)
                setTitle(nameRepo)
                setDescription("$nameRepo.zip")
            }
        dao.insertDownloadRepo(DownloadRepoEntity(userName = userName, nameRepo = nameRepo))
        return downloadManager.enqueue(request)
    }
}