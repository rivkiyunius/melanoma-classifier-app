package com.example.melanomaclassifier.ui.detail_periksa.presenter

import com.example.melanomaclassifier.ui.base.presenter.MvpPresenter
import com.example.melanomaclassifier.ui.detail_periksa.interactor.DetailPeriksaMvpInteractor
import com.example.melanomaclassifier.ui.detail_periksa.view.DetailPeriksaMvpView

interface DetailPeriksaMvpPresenter<V: DetailPeriksaMvpView, I: DetailPeriksaMvpInteractor> : MvpPresenter<V, I> {
    fun getDetailPeriksa(id: Int)
}