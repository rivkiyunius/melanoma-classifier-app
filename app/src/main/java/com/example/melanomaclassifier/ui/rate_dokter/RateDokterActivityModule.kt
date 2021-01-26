package com.example.melanomaclassifier.ui.rate_dokter

import com.example.melanomaclassifier.ui.rate_dokter.interactor.RateDokterInteractor
import com.example.melanomaclassifier.ui.rate_dokter.interactor.RateDokterMvpInteractor
import com.example.melanomaclassifier.ui.rate_dokter.presenter.RateDokterMvpPresenter
import com.example.melanomaclassifier.ui.rate_dokter.presenter.RateDokterPresenter
import com.example.melanomaclassifier.ui.rate_dokter.view.RateDokterMvpView
import dagger.Module
import dagger.Provides

@Module
class RateDokterActivityModule {

    @Provides
    fun providesRateDokterInteractor(interactor: RateDokterInteractor): RateDokterMvpInteractor =
        interactor

    @Provides
    fun provideRateDokterPresenter(presenter: RateDokterPresenter<RateDokterMvpView, RateDokterMvpInteractor>): RateDokterMvpPresenter<RateDokterMvpView, RateDokterMvpInteractor> =
        presenter
}