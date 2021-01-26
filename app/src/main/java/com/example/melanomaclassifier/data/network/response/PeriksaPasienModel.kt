package com.example.melanomaclassifier.data.network.response

data class PeriksaPasienModel(
    val create_at: String,
    val data_gambar_id: Int,
    val deskripsi: Any,
    val dokter_id: Any,
    val id: Int,
    val keluhan: String,
    val kesimpulan: Any,
    val pasien_id: Int,
    val status: Boolean,
    val update_at: String
)