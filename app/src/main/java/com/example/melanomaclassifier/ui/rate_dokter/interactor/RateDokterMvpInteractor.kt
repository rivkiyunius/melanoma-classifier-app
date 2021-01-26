package com.example.melanomaclassifier.ui.rate_dokter.interactor

import com.example.melanomaclassifier.data.network.request.RatingDokterRequest
import com.example.melanomaclassifier.data.network.response.*
import com.example.melanomaclassifier.ui.base.interactor.MvpInteractor
import io.reactivex.Observable

interface RateDokterMvpInteractor: MvpInteractor {
    fun postRating(request: RatingDokterRequest): Observable<BaseResponse<RatingDokterResponse>>
    fun getDataDoctor(id: Int): Observable<BaseResponse<DataDokterResponse>>
}