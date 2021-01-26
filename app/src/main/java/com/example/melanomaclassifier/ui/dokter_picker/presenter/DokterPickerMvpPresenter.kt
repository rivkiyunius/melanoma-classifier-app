package com.example.melanomaclassifier.ui.dokter_picker.presenter

import com.example.melanomaclassifier.ui.base.presenter.MvpPresenter
import com.example.melanomaclassifier.ui.dokter_picker.interactor.DokterPickerMvpInteractor
import com.example.melanomaclassifier.ui.dokter_picker.ui.DokterPickerMvpView

interface DokterPickerMvpPresenter<V: DokterPickerMvpView, I: DokterPickerMvpInteractor>: MvpPresenter<V, I> {
    fun getDataDokter()
}