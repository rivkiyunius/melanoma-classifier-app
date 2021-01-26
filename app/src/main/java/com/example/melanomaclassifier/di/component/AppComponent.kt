package com.example.melanomaclassifier.di.component

import android.app.Application
import com.example.melanomaclassifier.MelanomaApp
import com.example.melanomaclassifier.di.builder.ActivityBuilder
import com.example.melanomaclassifier.di.module.AppModule
import com.example.melanomaclassifier.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AndroidInjectionModule::class),
        (ActivityBuilder::class),
        (NetworkModule::class),
        (AppModule::class)]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MelanomaApp)
}