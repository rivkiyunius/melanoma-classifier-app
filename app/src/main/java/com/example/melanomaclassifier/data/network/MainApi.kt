package com.example.melanomaclassifier.data.network

import com.example.melanomaclassifier.data.network.request.LoginRequest
import com.example.melanomaclassifier.data.network.request.RatingDokterRequest
import com.example.melanomaclassifier.data.network.request.RegisterRequest
import com.example.melanomaclassifier.data.network.response.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.http.*

interface MainApi {

    @POST(ApiEndPoint.LOGIN_PASIEN)
    fun login(@Body request: LoginRequest): Observable<BaseResponse<LoginResponse>>

    @Multipart
    @POST(ApiEndPoint.CHECK_UP)
    fun checkUp(
        @Part imageFile: MultipartBody.Part?,
        @Part("keluhan") keluhan: RequestBody,
        @Part("id_dokter") idDokter: RequestBody
    ): Observable<BaseResponse<CheckUpResponse>>

    @GET(ApiEndPoint.USER)
    fun getUser(): Observable<BaseResponse<GetUserResponse>>

    @GET(ApiEndPoint.DATA_DOKTER)
    fun getDokter(): Observable<BaseResponse<DokterModelResponse>>

    @GET(ApiEndPoint.DATA_PERIKSA_PASIEN)
    fun getPeriksaPasien(): Observable<BaseResponse<ListPeriksaResponse>>

    @GET(ApiEndPoint.HISTORY)
    fun getHistory(): Observable<BaseResponse<ListPeriksaResponse>>

    @GET(ApiEndPoint.DETAIL_PERIKSA + "id={id}")
    fun getDetail(@Path("id") id: Int): Observable<BaseResponse<DetailPeriksaResponse>>

    @POST(ApiEndPoint.REGISTER)
    fun getRegister(@Body request: RegisterRequest): Observable<BaseResponse<RegisterResponse>>

    @Multipart
    @PUT(ApiEndPoint.EDIT)
    fun postUpdate(
        @Part imageFile: MultipartBody.Part?,
        @Part("nama") nama: RequestBody,
        @Part("no_telepon") noHp: RequestBody,
        @Part("jenis_kelamin") jk: RequestBody,
        @Part("umur") umur: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("tanggal_lahir") tanggalLahir: RequestBody,
        @Part("tempat_tanggal_lahir") tempatTanggalLahir: RequestBody,
        @Part("riwayat_penyakit") riwayatPenyakit: RequestBody
    ): Observable<BaseResponse<RegisterResponse>>

    @POST(ApiEndPoint.RATE_DOKTER)
    fun rateDokter(@Body request: RatingDokterRequest): Observable<BaseResponse<RatingDokterResponse>>

    @GET(ApiEndPoint.GET_DATA_DOKTER + "id={id}")
    fun dataDokter(@Path("id") id: Int): Observable<BaseResponse<DataDokterResponse>>

    companion object {
        fun create(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }
    }
}