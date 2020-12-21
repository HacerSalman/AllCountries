package com.hacer.allcountries.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    internal fun provideAppContext(application: Application): Context = application
}