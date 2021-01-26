package com.example.melanomaclassifier.data.network.request

import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject

open class BaseRequest {
    fun toJsonString(): String {
        val gson = GsonBuilder()
            .create()
        return gson.toJson(this)
    }

    fun toJson(): JSONObject? {
        var json: JSONObject? = null
        try {
            json = JSONObject(toJsonString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return json
    }
}