package com.example.melanomaclassifier.ui.main.view.fragment.history

import com.example.melanomaclassifier.ui.main.view.fragment.history.interactor.HistoryInteractor
import com.example.melanomaclassifier.ui.main.view.fragment.history.interactor.HistoryMvpInteractor
import com.example.melanomaclassifier.ui.main.view.fragment.history.presenter.HistoryMvpPresenter
import com.example.melanomaclassifier.ui.main.view.fragment.history.presenter.HistoryPresenter
import com.example.melanomaclassifier.ui.main.view.fragment.history.view.HistoryMvpView
import dagger.Module
import dagger.Provides

@Module
class HistoryFragmentModule {
    @Provides
    fun provideFragmentInteractor(interactor: HistoryInteractor): HistoryMvpInteractor = interactor

    @Provides
    fun provideFragmentPresenter(presenter: HistoryPresenter<HistoryMvpView, HistoryMvpInteractor>): HistoryMvpPresenter<HistoryMvpView, HistoryMvpInteractor> =
        presenter
}