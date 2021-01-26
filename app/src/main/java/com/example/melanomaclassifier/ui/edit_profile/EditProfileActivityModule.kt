package com.example.melanomaclassifier.ui.edit_profile

import com.example.melanomaclassifier.ui.edit_profile.interactor.EditProfileInteractor
import com.example.melanomaclassifier.ui.edit_profile.interactor.EditProfileMvpInteractor
import com.example.melanomaclassifier.ui.edit_profile.presenter.EditProfileMvpPresenter
import com.example.melanomaclassifier.ui.edit_profile.presenter.EditProfilePresenter
import com.example.melanomaclassifier.ui.edit_profile.view.EditProfileMvpView
import dagger.Module
import dagger.Provides

@Module
class EditProfileActivityModule {
    @Provides
    fun providesEditProfileInteractor(interactor: EditProfileInteractor): EditProfileMvpInteractor =
        interactor

    @Provides
    fun providesEditProfilePresenter(presenter: EditProfilePresenter<EditProfileMvpView, EditProfileMvpInteractor>): EditProfileMvpPresenter<EditProfileMvpView, EditProfileMvpInteractor> =
        presenter
}