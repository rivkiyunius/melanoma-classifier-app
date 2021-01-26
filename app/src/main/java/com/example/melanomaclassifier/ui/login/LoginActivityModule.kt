package com.example.melanomaclassifier.ui.login

import com.example.melanomaclassifier.ui.login.interactor.LoginInteractor
import com.example.melanomaclassifier.ui.login.interactor.LoginMvpInteractor
import com.example.melanomaclassifier.ui.login.presenter.LoginMvpPresenter
import com.example.melanomaclassifier.ui.login.presenter.LoginPresenter
import com.example.melanomaclassifier.ui.login.view.LoginMvpView
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {
    @Provides
    fun providesLoginInteractor(interactor: LoginInteractor): LoginMvpInteractor = interactor

    @Provides
    fun providesLoginPresenter(presenter: LoginPresenter<LoginMvpView, LoginMvpInteractor>): LoginMvpPresenter<LoginMvpView, LoginMvpInteractor> =
        presenter

}