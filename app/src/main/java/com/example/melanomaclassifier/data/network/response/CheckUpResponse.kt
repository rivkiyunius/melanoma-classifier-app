package com.example.melanomaclassifier.data.network.response

import com.google.gson.annotations.SerializedName

data class CheckUpResponse(
    @SerializedName("data_gambar_id")
    var dataGambarId: Int?,
    @SerializedName("dokter_id")
    var dokterId: Int?,
    @SerializedName("kesimpulan")
    var kesimpulan: String?,
    @SerializedName("deskripsi")
    var deskripsi: String?,
    @SerializedName("pasien_id")
    var pasienId: Int?
)