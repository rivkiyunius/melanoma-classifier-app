package com.example.melanomaclassifier.ui.main.view.fragment.home

import com.example.melanomaclassifier.ui.main.view.fragment.home.interactor.HomeInteractor
import com.example.melanomaclassifier.ui.main.view.fragment.home.interactor.HomeMvpInteractor
import com.example.melanomaclassifier.ui.main.view.fragment.home.presenter.HomeMvpPresenter
import com.example.melanomaclassifier.ui.main.view.fragment.home.presenter.HomePresenter
import com.example.melanomaclassifier.ui.main.view.fragment.home.view.HomeMvpView
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentModule {

    @Provides
    fun provideHomeInteractor(interactor: HomeInteractor): HomeMvpInteractor = interactor

    @Provides
    fun provideHomePresenter(presenter: HomePresenter<HomeMvpView, HomeMvpInteractor>): HomeMvpPresenter<HomeMvpView, HomeMvpInteractor> =
        presenter
}