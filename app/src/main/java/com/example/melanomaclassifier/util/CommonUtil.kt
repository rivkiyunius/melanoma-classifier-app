package com.example.melanomaclassifier.util

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toDrawable
import com.example.melanomaclassifier.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object CommonUtil {
    fun buildGson(): Gson {
        return GsonBuilder()
            .create()
    }

    fun showLoadingDialog(context: Context?): ProgressDialog{
        val progressDialog = ProgressDialog(context)
        progressDialog.let {
            it.show()
            it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.setContentView(R.layout.progress_dialog)
            it.isIndeterminate = true
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            return it
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getStartDate(startDate: String?): String? {
        val inputFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val outputFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LocalDate.parse(startDate, inputFormat).format(outputFormat)
    }

    @SuppressLint("SimpleDateFormat")
    fun String.formatDates(): String{
        val date = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val inputFormat =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
            val outputFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault())
            val date = LocalDate.parse(date, inputFormat)
            return outputFormat.format(date)
        } else {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
            val outputFormat = SimpleDateFormat("dd MMMM yyyy")
            val date = inputFormat.parse(date)
            return outputFormat.format(date)
        }
    }
}