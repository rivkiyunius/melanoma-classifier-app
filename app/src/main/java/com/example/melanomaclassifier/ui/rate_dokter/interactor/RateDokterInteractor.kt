package com.example.melanomaclassifier.ui.rate_dokter.interactor

import com.example.melanomaclassifier.data.network.ApiHelper
import com.example.melanomaclassifier.data.network.request.RatingDokterRequest
import com.example.melanomaclassifier.data.network.response.*
import com.example.melanomaclassifier.data.preferences.PreferencesHelper
import com.example.melanomaclassifier.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class RateDokterInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    apiHelper: ApiHelper
) : BaseInteractor(preferencesHelper, apiHelper), RateDokterMvpInteractor {
    override fun postRating(request: RatingDokterRequest): Observable<BaseResponse<RatingDokterResponse>> =
        apiHelper.rateDokter(request)

    override fun getDataDoctor(id: Int): Observable<BaseResponse<DataDokterResponse>> =
        apiHelper.getDokter(id)
}