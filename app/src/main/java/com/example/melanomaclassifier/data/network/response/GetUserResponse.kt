package com.example.melanomaclassifier.data.network.response


import com.google.gson.annotations.SerializedName

data class GetUserResponse(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gambar")
    val gambar: String,
    @SerializedName("jk")
    val jk: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("no_hp")
    val noHp: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("tanggal_lahir")
    val tanggalLahir: String,
    @SerializedName("tempat_tanggal_lahir")
    val tempatTanggalLahir: String,
    @SerializedName("umur")
    val umur: Int,
    @SerializedName("riwayat_penyakit")
    val riwayatPenyakit: String
)