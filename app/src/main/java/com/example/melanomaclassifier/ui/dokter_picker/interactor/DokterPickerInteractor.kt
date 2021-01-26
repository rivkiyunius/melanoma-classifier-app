package com.example.melanomaclassifier.ui.dokter_picker.interactor

import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.network.response.BaseResponse
import com.example.melanomaclassifier.data.network.response.DokterModelResponse
import com.example.melanomaclassifier.data.network.response.GetDataDoctorsResponse
import com.example.melanomaclassifier.data.preferences.PreferencesHelper
import com.example.melanomaclassifier.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class DokterPickerInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    apiHelper: ApiHelper
) : BaseInteractor(preferencesHelper, apiHelper), DokterPickerMvpInteractor {
    override fun getDokter(): Observable<BaseResponse<DokterModelResponse>> = apiHelper.getDokter()
}