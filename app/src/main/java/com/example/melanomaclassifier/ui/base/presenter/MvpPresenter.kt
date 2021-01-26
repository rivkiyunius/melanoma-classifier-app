package com.example.melanomaclassifier.ui.base.presenter

import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import com.example.melanomaclassifier.ui.base.view.MvpView
import retrofit2.HttpException

interface MvpPresenter<V: MvpView, I: MvpInteractor> {
    fun onAttach(view: V?)
    fun onDetach()
    fun getView(): V?
    fun handleApiError(error: HttpException)
    fun handleGenericError(errorMessage: String?)
    fun performUserLogout()
}