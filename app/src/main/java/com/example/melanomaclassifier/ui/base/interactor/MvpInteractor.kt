package com.example.melanomaclassifier.ui.base.interactor

interface MvpInteractor {
    fun isUserLoggedIn(): Boolean

    fun performUserLogout()
}