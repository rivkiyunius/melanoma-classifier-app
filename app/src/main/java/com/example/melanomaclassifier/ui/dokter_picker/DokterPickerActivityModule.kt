package com.example.melanomaclassifier.ui.dokter_picker

import com.example.melanomaclassifier.ui.dokter_picker.interactor.DokterPickerInteractor
import com.example.melanomaclassifier.ui.dokter_picker.interactor.DokterPickerMvpInteractor
import com.example.melanomaclassifier.ui.dokter_picker.presenter.DokterPickerMvpPresenter
import com.example.melanomaclassifier.ui.dokter_picker.presenter.DokterPickerPresenter
import com.example.melanomaclassifier.ui.dokter_picker.ui.DokterPickerMvpView
import dagger.Module
import dagger.Provides

@Module
class DokterPickerActivityModule {

    @Provides
    fun provideDokterPickerInteractor(interactor: DokterPickerInteractor): DokterPickerMvpInteractor = interactor

    @Provides
    fun provideDokterPickerPresenter(presenter: DokterPickerPresenter<DokterPickerMvpView, DokterPickerMvpInteractor>): DokterPickerMvpPresenter<DokterPickerMvpView, DokterPickerMvpInteractor> = presenter
}