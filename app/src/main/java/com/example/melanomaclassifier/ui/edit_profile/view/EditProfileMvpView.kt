package com.example.melanomaclassifier.ui.edit_profile.view

import com.example.melanomaclassifier.data.network.response.GetUserResponse
import com.example.melanomaclassifier.ui.base.view.MvpView

interface EditProfileMvpView: MvpView {
    fun onSuccess()
    fun loadData(data: GetUserResponse)
}