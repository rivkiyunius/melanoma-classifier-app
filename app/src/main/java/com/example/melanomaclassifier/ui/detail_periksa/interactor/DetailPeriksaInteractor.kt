package com.example.melanomaclassifier.ui.detail_periksa.interactor

import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.DetailPeriksaResponse
import com.example.melanomaclassifier.data.preferences.PreferencesHelper
import com.example.melanomaclassifier.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class DetailPeriksaInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    apiHelper: ApiHelper
) : BaseInteractor(preferencesHelper, apiHelper), DetailPeriksaMvpInteractor {
    override fun getDetailPeriksa(id: Int): Observable<BaseResponse<DetailPeriksaResponse>> = apiHelper.getDetailPeriksa(id)
}