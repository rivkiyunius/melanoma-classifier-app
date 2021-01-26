package com.example.melanomaclassifier.ui.detail_periksa

import com.example.melanomaclassifier.ui.detail_periksa.interactor.DetailPeriksaInteractor
import com.example.melanomaclassifier.ui.detail_periksa.interactor.DetailPeriksaMvpInteractor
import com.example.melanomaclassifier.ui.detail_periksa.presenter.DetailPeriksaMvpPresenter
import com.example.melanomaclassifier.ui.detail_periksa.presenter.DetailPeriksaPresenter
import com.example.melanomaclassifier.ui.detail_periksa.view.DetailPeriksaMvpView
import dagger.Module
import dagger.Provides

@Module
class DetailPeriksaActivityModule {

    @Provides
    fun provideDetailPeriksaInteractor(interactor: DetailPeriksaInteractor): DetailPeriksaMvpInteractor = interactor

    @Provides
    fun provideDetailPeriksaPresenter(presenter: DetailPeriksaPresenter<DetailPeriksaMvpView, DetailPeriksaMvpInteractor>): DetailPeriksaMvpPresenter<DetailPeriksaMvpView, DetailPeriksaMvpInteractor> = presenter
}