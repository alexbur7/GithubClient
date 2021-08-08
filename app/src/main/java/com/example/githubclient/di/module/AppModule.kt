package com.example.githubclient.di.module

import android.app.DownloadManager
import android.content.Context
import androidx.room.Room
import com.example.githubclient.base.App
import com.example.githubclient.db.DownloadRepoDao
import com.example.githubclient.db.DownloadReposDB
import com.example.githubclient.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @AppScope
    @Provides
    fun providesContext(app: App): Context {
        return app.applicationContext
    }

    @Provides
    fun providesDownloadManager(context: Context): DownloadManager {
        return context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

    @AppScope
    @Provides
    fun providesRoomDatabase(context: Context): DownloadReposDB {
        return Room.databaseBuilder(
            context,
            DownloadReposDB::class.java,
            "download_repos.db"
        ).build()
    }

    @Provides
    fun provideGroupPostDao(db: DownloadReposDB): DownloadRepoDao = db.getDao()

}