package com.example.melanomaclassifier.ui.main.view.fragment

import com.example.melanomaclassifier.ui.main.view.fragment.history.HistoryFragmentModule
import com.example.melanomaclassifier.ui.main.view.fragment.history.view.HistoryFragment
import com.example.melanomaclassifier.ui.main.view.fragment.home.HomeFragmentModule
import com.example.melanomaclassifier.ui.main.view.fragment.home.view.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsProvider {
    @ContributesAndroidInjector(modules = [(HomeFragmentModule::class)])
    internal abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [(HistoryFragmentModule::class)])
    internal abstract fun provideHistoryFragment(): HistoryFragment
}