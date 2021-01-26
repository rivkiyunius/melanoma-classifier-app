package com.example.melanomaclassifier.data.network

import com.example.melanomaclassifier.data.network.request.*
import com.example.melanomaclassifier.data.network.response.*
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val api: MainApi) : ApiHelper {
    override fun login(request: LoginRequest): Observable<BaseResponse<LoginResponse>> =
        api.login(request)

    override fun checkUp(request: CheckUpRequest): Observable<BaseResponse<CheckUpResponse>> {
        var imageFileBody: MultipartBody.Part? = null
        if (request.file != null) {
            val requestBody = RequestBody.create(MediaType.get("multipart/form-data"), request.file)
            imageFileBody =
                MultipartBody.Part.createFormData("file_upload", request.file!!.name, requestBody)
        }

        val keluhan = createPartFromString(request.keluhan ?: "")
        val idDokter = createPartFromString(request.idDokter ?: "")

        return api.checkUp(imageFileBody, keluhan, idDokter)
    }

    private fun createPartFromString(param: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), param)
    }

    override fun getUser() = api.getUser()

    override fun getDokter(): Observable<BaseResponse<DokterModelResponse>> = api.getDokter()

    override fun getDokter(id: Int): Observable<BaseResponse<DataDokterResponse>> =
        api.dataDokter(id)

    override fun getListPeriksaPasien(): Observable<BaseResponse<ListPeriksaResponse>> =
        api.getPeriksaPasien()

    override fun getHistory(): Observable<BaseResponse<ListPeriksaResponse>> = api.getHistory()

    override fun getDetailPeriksa(id: Int): Observable<BaseResponse<DetailPeriksaResponse>> =
        api.getDetail(id)

    override fun register(request: RegisterRequest): Observable<BaseResponse<RegisterResponse>> =
        api.getRegister(request)

    override fun update(request: EditProfileRequest): Observable<BaseResponse<RegisterResponse>> {
        var imageFileBody: MultipartBody.Part? = null
        if (request.file != null) {
            val requestBody = RequestBody.create(MediaType.get("multipart/form-data"), request.file)
            imageFileBody =
                MultipartBody.Part.createFormData("file_upload", request.file!!.name, requestBody)
        }else{
            val requestBody = RequestBody.create(MediaType.get("multipart/form-data"), "")
            imageFileBody =
                MultipartBody.Part.createFormData("file_upload", "", requestBody)
        }

        val nama = createPartFromString(request.nama ?: "")
        val jk = createPartFromString(request.jenisKelamin ?: "")
        val alamat = createPartFromString(request.alamat ?: "")
        val noHp = createPartFromString(request.noTelepon ?: "")
        val umur = createPartFromString(request.umur.toString())
        val tempatTanggalLahir = createPartFromString(request.tempatTanggalLahir ?: "")
        val tanggalLahir = createPartFromString(request.tanggalLahir ?: "")
        val riwayatPenyakit = createPartFromString(request.riwayatPenyakit ?: "")
        return api.postUpdate(
            imageFileBody,
            nama,
            noHp,
            jk,
            umur,
            alamat,
            tanggalLahir,
            tempatTanggalLahir,
            riwayatPenyakit
        )
    }

    override fun rateDokter(request: RatingDokterRequest): Observable<BaseResponse<RatingDokterResponse>> =
        api.rateDokter(request)
}