package com.example.melanomaclassifier.ui.rate_dokter.view

import android.os.Bundle
import android.util.Log
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.data.network.response.DataDoctors
import com.example.melanomaclassifier.data.network.response.DataDokterResponse
import com.example.melanomaclassifier.data.network.response.GetDataDoctorsResponse
import com.example.melanomaclassifier.ui.base.view.BaseActivity
import com.example.melanomaclassifier.ui.main.view.MainActivity
import com.example.melanomaclassifier.ui.rate_dokter.interactor.RateDokterMvpInteractor
import com.example.melanomaclassifier.ui.rate_dokter.presenter.RateDokterMvpPresenter
import kotlinx.android.synthetic.main.activity_rate_dokter.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class RateDokterActivity : BaseActivity(), RateDokterMvpView {

    companion object{
        const val ID_DOKTER = "ID_DOKTER"
    }

    @Inject
    lateinit var presenter: RateDokterMvpPresenter<RateDokterMvpView, RateDokterMvpInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_dokter)
        setup()
    }

    private fun setup() {
        val idDokter = intent.getStringExtra(ID_DOKTER)
        presenter.onAttach(this)
        presenter.loadDataDokter(idDokter.toInt())
        buttonSave.onClick {
            val rating = ratingBar.rating
            val notes = editTextNote.text.toString()
            presenter.postRating(rating.toInt(), notes, idDokter.toInt())
        }
    }

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String) {}

    override fun redirectToHome() {
        startActivity<MainActivity>()
        finish()
    }

    override fun loadDataDokter(data: DataDokterResponse) {
        tv_name_doctor.text = data.nama
    }
}