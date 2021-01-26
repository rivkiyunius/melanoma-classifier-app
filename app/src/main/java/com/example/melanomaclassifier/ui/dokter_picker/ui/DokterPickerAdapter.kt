package com.example.melanomaclassifier.ui.dokter_picker.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.data.network.response.DokterModel
import kotlinx.android.synthetic.main.item_view_dokter.view.*

class DokterPickerAdapter(val periksa: List<DokterModel>, val unit: (DokterModel) -> Unit) : RecyclerView.Adapter<DokterPickerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_dokter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = periksa.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindModel(periksa[position], unit)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val namaDokter = itemView.textViewNamaDokter

        fun bindModel(dokter: DokterModel, unit: (DokterModel) -> Unit){
            namaDokter.text = dokter.nama
            itemView.setOnClickListener {
                unit(dokter)
            }
        }
    }
}