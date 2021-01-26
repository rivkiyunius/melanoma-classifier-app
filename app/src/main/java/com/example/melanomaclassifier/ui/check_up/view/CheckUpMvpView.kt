package com.example.melanomaclassifier.ui.check_up.view

import com.example.melanomaclassifier.data.network.response.GetDataDoctorsResponse
import com.example.melanomaclassifier.ui.base.view.MvpView

interface CheckUpMvpView: MvpView {
    fun redirectToHome()
}