package com.example.melanomaclassifier.ui.main.view.fragment.home.interactor

import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.GetUserResponse
import com.example.melanomaclassifier.data.network.response.ListPeriksaResponse
import com.example.melanomaclassifier.data.preferences.PreferencesHelper
import com.example.melanomaclassifier.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class HomeInteractor @Inject internal constructor(
    apiHelper: ApiHelper,
    preferencesHelper: PreferencesHelper
) : BaseInteractor(preferencesHelper, apiHelper), HomeMvpInteractor {
    override fun getUser(): Observable<BaseResponse<GetUserResponse>> = apiHelper.getUser()
    override fun getPeriksaPasien(): Observable<BaseResponse<ListPeriksaResponse>> = apiHelper.getListPeriksaPasien()
    override fun logout() {
        preferencesHelper.resetAllPreferences()
    }

}