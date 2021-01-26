package com.example.melanomaclassifier.ui.check_up

import com.example.melanomaclassifier.ui.check_up.interactor.CheckUpInteractor
import com.example.melanomaclassifier.ui.check_up.interactor.CheckUpMvpInteractor
import com.example.melanomaclassifier.ui.check_up.presenter.CheckUpMvpPresenter
import com.example.melanomaclassifier.ui.check_up.presenter.CheckUpPresenter
import com.example.melanomaclassifier.ui.check_up.view.CheckUpMvpView
import dagger.Module
import dagger.Provides

@Module
class CheckUpActivityModule {
    @Provides
    fun provideCheckUpInteractor(interactor: CheckUpInteractor): CheckUpMvpInteractor = interactor

    @Provides
    fun provideCheckUpPresenter(presenter: CheckUpPresenter<CheckUpMvpView, CheckUpMvpInteractor>): CheckUpMvpPresenter<CheckUpMvpView, CheckUpMvpInteractor> = presenter
}