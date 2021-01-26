package com.example.melanomaclassifier.data.network

import android.util.Log
import com.example.melanomaclassifier.data.preferences.AppPreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class RequestInterceptor @Inject constructor(var preferencesHelper: AppPreferencesHelper): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val token = preferencesHelper.getToken()

        Log.d("ACCESS TOKEN", token.toString())

        val request = original.newBuilder()
            .header("api-token", token.toString())
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }
}