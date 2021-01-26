package com.example.melanomaclassifier.data.network.request

import com.google.gson.annotations.SerializedName
import java.io.File

class EditProfileRequest : BaseRequest() {
    var file: File? = null
    var nama: String? = null
    var noTelepon: String? = null
    var jenisKelamin: String? = null
    var alamat: String? = null
    var umur: Int? = 0
    var tanggalLahir: String? = null
    var riwayatPenyakit: String? = null
    var tempatTanggalLahir: String? = null

}