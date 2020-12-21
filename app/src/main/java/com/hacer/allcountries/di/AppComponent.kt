package com.hacer.allcountries.di

import android.app.Application
import com.hacer.allcountries.core.BaseApplication
import com.hacer.allcountries.di.module.ActivityBindingsModule
import com.hacer.allcountries.di.module.FragmentBindingsModule
import com.hacer.allcountries.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingsModule::class,
        FragmentBindingsModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}