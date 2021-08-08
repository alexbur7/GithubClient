package com.example.githubclient.di

import com.example.githubclient.base.App
import com.example.githubclient.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [AppModule::class, NetworkModule::class, AppBuilderModule::class,
        AndroidSupportInjectionModule::class, ViewModelModule::class, BindsModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>

}