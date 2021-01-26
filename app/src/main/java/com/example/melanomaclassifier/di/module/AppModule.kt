package com.example.melanomaclassifier.di.module

import android.app.Application
import android.content.Context
import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.network.AppApiHelper
import com.example.melanomaclassifier.data.preferences.AppPreferencesHelper
import com.example.melanomaclassifier.data.preferences.PreferencesHelper
import com.example.melanomaclassifier.di.PreferenceInfo
import com.example.melanomaclassifier.util.AppConstants
import com.example.melanomaclassifier.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @PreferenceInfo
    internal fun providePrefFileName(): String = AppConstants.PREF_NAME

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper =
        appPreferencesHelper

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()
}