package com.example.melanomaclassifier.ui.main.view.fragment.home.view.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.data.network.response.PeriksaPasienModel
import com.example.melanomaclassifier.util.CommonUtil.formatDates
import kotlinx.android.synthetic.main.item_view.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class RvPeriksaPasienAdapter(
    val periksa: List<PeriksaPasienModel>,
    val listener: (PeriksaPasienModel) -> Unit
) :
    RecyclerView.Adapter<RvPeriksaPasienAdapter.PeriksaPasienVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriksaPasienVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return PeriksaPasienVH(view)
    }

    override fun getItemCount(): Int = periksa.size


    override fun onBindViewHolder(holder: PeriksaPasienVH, position: Int) {
        holder.bindModel(periksa[position], listener)
    }

    inner class PeriksaPasienVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tanggalPeriksa = itemView.tv_tgl_periksa
        val keluhan = itemView.tv_keluhan

        @SuppressLint("SetTextI18n")
        fun bindModel(periksa: PeriksaPasienModel, listener: (PeriksaPasienModel) -> Unit) {
            keluhan.text = "Keluhan: ${periksa.keluhan}"
            tanggalPeriksa.text = "Tanggal Periksa: ${periksa.create_at.formatDates()}"
            itemView.setOnClickListener {
                listener(periksa)
            }
        }
    }
}