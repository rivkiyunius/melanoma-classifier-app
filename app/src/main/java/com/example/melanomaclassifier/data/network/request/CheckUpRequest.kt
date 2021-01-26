package com.example.melanomaclassifier.data.network.request

import java.io.File

class CheckUpRequest : BaseRequest() {
    var file: File? = null
    var keluhan: String? = null
    var idDokter: String? = null
}