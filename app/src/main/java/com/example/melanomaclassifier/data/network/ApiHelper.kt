package com.example.melanomaclassifier.data.network

import com.example.melanomaclassifier.data.network.request.*
import com.example.melanomaclassifier.data.network.response.*
import io.reactivex.Observable

interface ApiHelper {
    fun login(request: LoginRequest): Observable<BaseResponse<LoginResponse>>

    fun checkUp(request: CheckUpRequest): Observable<BaseResponse<CheckUpResponse>>

    fun getUser(): Observable<BaseResponse<GetUserResponse>>

    fun getDokter(): Observable<BaseResponse<DokterModelResponse>>

    fun getListPeriksaPasien(): Observable<BaseResponse<ListPeriksaResponse>>

    fun getHistory(): Observable<BaseResponse<ListPeriksaResponse>>

    fun getDetailPeriksa(id: Int): Observable<BaseResponse<DetailPeriksaResponse>>

    fun register(request: RegisterRequest): Observable<BaseResponse<RegisterResponse>>

    fun update(request: EditProfileRequest): Observable<BaseResponse<RegisterResponse>>

    fun rateDokter(request: RatingDokterRequest): Observable<BaseResponse<RatingDokterResponse>>

    fun getDokter(id: Int): Observable<BaseResponse<DataDokterResponse>>
}