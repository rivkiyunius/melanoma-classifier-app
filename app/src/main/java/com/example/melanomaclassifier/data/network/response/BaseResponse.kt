package com.example.melanomaclassifier.data.network.response

class BaseResponse<T>(val status: Int, val result: T, val error: String?) {
    fun isSuccessfull(): Boolean = status == 200 || status == 201
}