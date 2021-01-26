package com.example.melanomaclassifier.data.network.response

data class LoginResponse(
    val id: Int,
    val email: String,
    val jwt_token: String,
    val nama: String
)