package com.example.melanomaclassifier.ui.detail_periksa.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.melanomaclassifier.BuildConfig
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.data.network.response.DetailPeriksaResponse
import com.example.melanomaclassifier.ui.base.view.BaseActivity
import com.example.melanomaclassifier.ui.detail_periksa.interactor.DetailPeriksaMvpInteractor
import com.example.melanomaclassifier.ui.detail_periksa.presenter.DetailPeriksaMvpPresenter
import com.example.melanomaclassifier.ui.rate_dokter.view.RateDokterActivity
import com.example.melanomaclassifier.util.CommonUtil.formatDates
import com.example.melanomaclassifier.util.loadImage
import kotlinx.android.synthetic.main.activity_detail_periksa.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class DetailPeriksaActivity : BaseActivity(), DetailPeriksaMvpView {

    companion object {
        val ID_PERIKSA = "ID_PERIKSA"
        val STATUS = "STATUS"
    }

    @Inject
    lateinit var presenter: DetailPeriksaMvpPresenter<DetailPeriksaMvpView, DetailPeriksaMvpInteractor>
    private var status = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_periksa)
        setupToolbar("Detail Periksa")
        presenter.onAttach(this)

        val data = intent.getIntExtra(ID_PERIKSA, 0)
        status = intent.getBooleanExtra(STATUS, false)
        presenter.getDetailPeriksa(data)
    }

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String) {}

    override fun showDetail(data: DetailPeriksaResponse) {
        imageDetailPeriksa.loadImage(BuildConfig.BASE_URL_IMAGE + data.gambar)
        textViewNamaDokter.text = data.namaDokter
        textViewStatusPeriksa.text = if (data.status) "Sudah Diperiksa" else "Belum Diperiksa"
        textViewTanggalPeriksa.text = data.tanggalPeriksa?.formatDates()
        textViewKeluhan.text = data.keluhan
        statusDetail(data)
    }

    private fun statusDetail(data: DetailPeriksaResponse) {
        when (status) {
            true -> {
                buttonRating.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        startActivity<RateDokterActivity>(
                            RateDokterActivity.ID_DOKTER to data.idDokter
                        )
                    }
                }
                buttonHubungiDokter.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.setData(Uri.parse("tel:" + data.nomorTelepon))
                        startActivity(intent)
                    }
                }
                layoutKesimpulan.visibility = View.VISIBLE
                view.visibility = View.VISIBLE
                textViewKesimpulan.text = data.kesimpulan.toString()
                textViewDeskripsi.text = data.deskripsi
            }
            false -> {
                layoutKesimpulan.visibility = View.GONE
                view.visibility = View.GONE
                buttonRating.visibility = View.GONE
                buttonHubungiDokter.visibility = View.GONE
            }
        }
    }
}