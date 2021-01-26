package com.example.melanomaclassifier.util

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.io.File
import java.lang.Exception

internal fun ImageView.loadImage(file: File): Boolean {

    var loaded = false

    if (file.exists()) {
        Picasso.get()
            .load(file)
            .into(this)

        loaded = true
    }

    return loaded
}

internal fun ImageView.loadImage(url: String) {
    val imageView = this
    Picasso.get().load(url).fetch(object : Callback {
        override fun onError(e: Exception?) {
            //TODO: handle error here
        }
        override fun onSuccess() {
            alpha = 0f
            Picasso.get()
                .load(url)
                .into(imageView)
            animate().setDuration(500).alpha(1f).start()
        }

    })
    /*Picasso.get()
        .load(url)
        .into(this)*/
}