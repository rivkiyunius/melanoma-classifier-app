package com.example.melanomaclassifier.ui.dokter_picker.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.data.network.response.DokterModel
import com.example.melanomaclassifier.data.network.response.DokterModelResponse
import com.example.melanomaclassifier.ui.base.view.BaseActivity
import com.example.melanomaclassifier.ui.check_up.view.CheckUpActivity
import com.example.melanomaclassifier.ui.dokter_picker.interactor.DokterPickerMvpInteractor
import com.example.melanomaclassifier.ui.dokter_picker.presenter.DokterPickerMvpPresenter
import kotlinx.android.synthetic.main.activity_doctor_picker.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class DokterPickerActivity: BaseActivity(), DokterPickerMvpView {

    @Inject
    lateinit var presenter: DokterPickerMvpPresenter<DokterPickerMvpView, DokterPickerMvpInteractor>
    private val dokter: MutableList<DokterModel> = mutableListOf()
    private lateinit var recyclerView: DokterPickerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_picker)
        presenter.onAttach(this)

        setup()
    }

    private fun setup(){
        val linearLayout = LinearLayoutManager(this@DokterPickerActivity)
        rvDokterPicker.apply {
            layoutManager = linearLayout
            recyclerView = DokterPickerAdapter(dokter){
                startActivity<CheckUpActivity>(
                    "dokter" to it
                )
                finish()
            }
            adapter = recyclerView
        }
        presenter.getDataDokter()
    }

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String) {}

    override fun showDataDokter(data: DokterModelResponse) {
        dokter.clear()
        dokter.addAll(data.data)
        recyclerView.notifyDataSetChanged()
    }
}