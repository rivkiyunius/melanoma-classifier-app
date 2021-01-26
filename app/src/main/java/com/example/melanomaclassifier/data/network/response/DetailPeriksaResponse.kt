package com.example.melanomaclassifier.data.network.response

import com.google.gson.annotations.SerializedName

data class DetailPeriksaResponse(
    val alamat: String?,
    val gambar: String?,
    val keluhan: String?,
    val kesimpulan: String?,
    @SerializedName("nama")
    val namaDokter: String?,
    @SerializedName("dokter_id")
    val idDokter: String?,
    @SerializedName("create_at")
    val tanggalPeriksa: String?,
    val status: Boolean,
    val deskripsi: String,
    @SerializedName("no_hp")
    val nomorTelepon: String)