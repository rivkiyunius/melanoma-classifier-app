package com.example.melanomaclassifier.ui.rate_dokter.presenter

import com.example.melanomaclassifier.ui.base.presenter.MvpPresenter
import com.example.melanomaclassifier.ui.rate_dokter.interactor.RateDokterMvpInteractor
import com.example.melanomaclassifier.ui.rate_dokter.view.RateDokterMvpView

interface RateDokterMvpPresenter<V: RateDokterMvpView, I: RateDokterMvpInteractor>: MvpPresenter<V, I> {
    fun postRating(rating: Int, notes: String, idDokter: Int)
    fun loadDataDokter(id: Int)
}