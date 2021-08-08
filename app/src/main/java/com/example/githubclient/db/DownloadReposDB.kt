package com.example.githubclient.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubclient.model.DownloadRepoEntity

@Database(entities = [DownloadRepoEntity::class], version = 1, exportSchema = false)
abstract class DownloadReposDB : RoomDatabase() {

    abstract fun getDao(): DownloadRepoDao
}