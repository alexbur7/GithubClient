package com.example.githubclient.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "download_repos")
data class DownloadRepoEntity(
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "name_repo")
    val nameRepo: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long? = null
}
