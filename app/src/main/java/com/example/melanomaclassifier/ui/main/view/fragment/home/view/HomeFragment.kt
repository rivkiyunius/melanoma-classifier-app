package com.example.melanomaclassifier.ui.main.view.fragment.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melanomaclassifier.BuildConfig
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.data.network.response.GetUserResponse
import com.example.melanomaclassifier.data.network.response.ListPeriksaResponse
import com.example.melanomaclassifier.data.network.response.PeriksaPasienModel
import com.example.melanomaclassifier.ui.base.view.BaseFragment
import com.example.melanomaclassifier.ui.detail_periksa.view.DetailPeriksaActivity
import com.example.melanomaclassifier.ui.edit_profile.view.EditProfileActivity
import com.example.melanomaclassifier.ui.login.view.LoginActivity
import com.example.melanomaclassifier.ui.main.view.fragment.home.interactor.HomeMvpInteractor
import com.example.melanomaclassifier.ui.main.view.fragment.home.presenter.HomeMvpPresenter
import com.example.melanomaclassifier.ui.main.view.fragment.home.view.adapter.RvPeriksaPasienAdapter
import com.example.melanomaclassifier.util.loadImage
import kotlinx.android.synthetic.main.app_bar_transparent.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeMvpView {

    @Inject
    lateinit var presenter: HomeMvpPresenter<HomeMvpView, HomeMvpInteractor>
    private val periksa: MutableList<PeriksaPasienModel> = mutableListOf()
    private lateinit var recyclerViewAdapter: RvPeriksaPasienAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment {
            return HomeFragment().apply {
                arguments = Bundle()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.let {
            it.onAttach(this)
            it.showUser()
        }
        setUp()
    }

    override fun setUp() {
        val linearLayout = LinearLayoutManager(activity)
        rv_periksa_pasien.apply {
            layoutManager = linearLayout
            recyclerViewAdapter = RvPeriksaPasienAdapter(periksa){
                startActivity<DetailPeriksaActivity>(
                    DetailPeriksaActivity.ID_PERIKSA to it.id,
                    DetailPeriksaActivity.STATUS to it.status
                )
            }
            adapter = recyclerViewAdapter
        }
        presenter.showPeriksaPasien()

        img_logout.setOnClickListener {
            alert("Apakah anda yakin ingin keluar?") {
                positiveButton("Ya") { dialog ->
                    presenter.logout()
                    dialog.dismiss()
                }

                negativeButton("Tidak") { dialog ->
                    dialog.dismiss()
                }
            }.show()
        }

        img_edit.setOnClickListener {
            startActivity<EditProfileActivity>()
        }
    }

    override fun showUser(data: GetUserResponse) {
        data.let {
            img_profile.loadImage(BuildConfig.BASE_URL_IMAGE + data.gambar)
            tv_name.text = it.nama
            tv_email.text = it.email
        }
    }

    override fun showPeriksaPasien(data: ListPeriksaResponse) {
        if(data.periksa.isEmpty()){
            data_empty.visibility = View.VISIBLE
            list_data.visibility = View.GONE
        }else{
            data_empty.visibility = View.GONE
            list_data.visibility = View.VISIBLE
            periksa.clear()
            periksa.addAll(data.periksa)
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }

    override fun redirectToLogin() {
        startActivity<LoginActivity>()
    }

}