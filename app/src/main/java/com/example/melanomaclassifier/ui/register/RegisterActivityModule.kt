package com.example.melanomaclassifier.ui.register

import com.example.melanomaclassifier.ui.register.interactor.RegisterInteractor
import com.example.melanomaclassifier.ui.register.interactor.RegisterMvpInteractor
import com.example.melanomaclassifier.ui.register.presenter.RegisterMvpPresenter
import com.example.melanomaclassifier.ui.register.presenter.RegisterPresenter
import com.example.melanomaclassifier.ui.register.view.RegisterMvpView
import dagger.Module
import dagger.Provides

@Module
class RegisterActivityModule {

    @Provides
    fun provideRegisterInteractor(interactor: RegisterInteractor): RegisterMvpInteractor = interactor

    @Provides
    fun provideRegisterPresenter(presenter: RegisterPresenter<RegisterMvpView, RegisterMvpInteractor>): RegisterMvpPresenter<RegisterMvpView, RegisterMvpInteractor> = presenter
}