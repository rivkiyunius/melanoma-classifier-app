package com.example.melanomaclassifier.ui.splashscreen.interactor

import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.preferences.PreferencesHelper
import com.example.melanomaclassifier.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class SplashScreenInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    apiHelper: ApiHelper
) : BaseInteractor(preferencesHelper, apiHelper), SplashScreenMvpInteractor {
    override fun hasLoggedIn(): Boolean {
        return !preferencesHelper.getToken().isNullOrEmpty()
    }
}