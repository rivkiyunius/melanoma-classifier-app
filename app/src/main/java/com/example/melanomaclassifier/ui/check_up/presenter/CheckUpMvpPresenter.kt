package com.example.melanomaclassifier.ui.check_up.presenter

import com.example.melanomaclassifier.ui.base.presenter.MvpPresenter
import com.example.melanomaclassifier.ui.check_up.interactor.CheckUpMvpInteractor
import com.example.melanomaclassifier.ui.check_up.view.CheckUpMvpView
import java.io.File

interface CheckUpMvpPresenter<V: CheckUpMvpView, I: CheckUpMvpInteractor>: MvpPresenter<V, I> {
    fun checkUp(fileUpload: File?, keluhan: String, idDokter: String)
}