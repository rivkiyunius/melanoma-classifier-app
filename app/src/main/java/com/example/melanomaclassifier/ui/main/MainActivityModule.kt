package com.example.melanomaclassifier.ui.main

import com.example.melanomaclassifier.ui.main.interactor.MainInteractor
import com.example.melanomaclassifier.ui.main.interactor.MainMvpInteractor
import com.example.melanomaclassifier.ui.main.presenter.MainMvpPresenter
import com.example.melanomaclassifier.ui.main.presenter.MainPresenter
import com.example.melanomaclassifier.ui.main.view.MainMvpView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(interactor: MainInteractor): MainMvpInteractor = interactor

    @Provides
    internal fun provideMainPresenter(presenter: MainPresenter<MainMvpView, MainMvpInteractor>): MainMvpPresenter<MainMvpView, MainMvpInteractor> = presenter
}