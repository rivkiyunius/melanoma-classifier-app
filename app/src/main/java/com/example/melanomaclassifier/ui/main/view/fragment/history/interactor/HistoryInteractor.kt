package com.example.melanomaclassifier.ui.main.view.fragment.history.interactor

import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.ListPeriksaResponse
import com.example.melanomaclassifier.data.preferences.AppPreferencesHelper
import com.example.melanomaclassifier.data.preferences.PreferencesHelper
import com.example.melanomaclassifier.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class HistoryInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    apiHelper: ApiHelper
) : BaseInteractor(preferencesHelper, apiHelper), HistoryMvpInteractor {
    override fun getHistoryPeriksa(): Observable<BaseResponse<ListPeriksaResponse>> = apiHelper.getHistory()

}