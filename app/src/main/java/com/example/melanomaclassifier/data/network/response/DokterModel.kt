package com.example.melanomaclassifier.data.network.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DokterModel(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("create_at")
    val createAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jk")
    val jk: String,
    @SerializedName("modified_at")
    val modifiedAt: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("no_hp")
    val noHp: String,
    @SerializedName("password")
    val password: String
): Parcelable