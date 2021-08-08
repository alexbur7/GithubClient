package com.example.githubclient.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.githubclient.model.DownloadRepoEntity

@Dao
interface DownloadRepoDao {

    @Insert
    suspend fun insertDownloadRepo(downloadRepoEntity: DownloadRepoEntity)

    @Query("SELECT * FROM download_repos")
    suspend fun getAllDownloadRepo(): List<DownloadRepoEntity>
}