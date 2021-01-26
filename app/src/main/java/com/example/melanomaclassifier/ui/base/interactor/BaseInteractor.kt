package com.example.melanomaclassifier.ui.base.interactor

import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.preferences.PreferencesHelper

open class BaseInteractor() : MvpInteractor {
    protected lateinit var preferencesHelper: PreferencesHelper
    protected lateinit var apiHelper: ApiHelper

    constructor(preferencesHelper: PreferencesHelper, apiHelper: ApiHelper) : this() {
        this.preferencesHelper = preferencesHelper
        this.apiHelper = apiHelper
    }

    override fun isUserLoggedIn(): Boolean = !this.preferencesHelper.getToken().isNullOrEmpty()

    override fun performUserLogout() {
        preferencesHelper.resetAllPreferences()
    }
}