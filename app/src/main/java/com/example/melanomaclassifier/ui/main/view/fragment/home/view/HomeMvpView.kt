package com.example.melanomaclassifier.ui.main.view.fragment.home.view

import com.example.melanomaclassifier.data.network.response.GetUserResponse
import com.example.melanomaclassifier.data.network.response.ListPeriksaResponse
import com.example.melanomaclassifier.ui.base.view.MvpView

interface HomeMvpView : MvpView {
    fun showUser(data: GetUserResponse)
    fun showPeriksaPasien(data: ListPeriksaResponse)
    fun redirectToLogin()
}