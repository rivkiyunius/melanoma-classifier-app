package com.example.melanomaclassifier.ui.main.view.fragment.history.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.data.network.response.PeriksaPasienModel
import com.example.melanomaclassifier.util.CommonUtil.formatDates
import kotlinx.android.synthetic.main.item_view.view.*

class RvHistoryAdapter(val history: List<PeriksaPasienModel>, val listener: (PeriksaPasienModel) -> Unit) :
    RecyclerView.Adapter<RvHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = history.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindModel(history[position], listener)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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