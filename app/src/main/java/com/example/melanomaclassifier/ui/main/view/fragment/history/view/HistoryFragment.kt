package com.example.melanomaclassifier.ui.main.view.fragment.history.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.data.network.response.ListPeriksaResponse
import com.example.melanomaclassifier.data.network.response.PeriksaPasienModel
import com.example.melanomaclassifier.ui.base.view.BaseFragment
import com.example.melanomaclassifier.ui.detail_periksa.view.DetailPeriksaActivity
import com.example.melanomaclassifier.ui.main.view.fragment.history.interactor.HistoryMvpInteractor
import com.example.melanomaclassifier.ui.main.view.fragment.history.presenter.HistoryMvpPresenter
import com.example.melanomaclassifier.ui.main.view.fragment.history.view.adapter.RvHistoryAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class HistoryFragment : BaseFragment(), HistoryMvpView {

    @Inject
    lateinit var presenter: HistoryMvpPresenter<HistoryMvpView, HistoryMvpInteractor>
    private val history: MutableList<PeriksaPasienModel> = mutableListOf()
    private lateinit var recyclerViewAdapter: RvHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): HistoryFragment {
            return HistoryFragment().apply {
                arguments = Bundle()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
    }

    override fun setUp() {
        val linearLayout = LinearLayoutManager(activity)
        rv_history.apply {
            layoutManager = linearLayout
            recyclerViewAdapter = RvHistoryAdapter(history) {
                startActivity<DetailPeriksaActivity>(
                    DetailPeriksaActivity.ID_PERIKSA to it.id,
                    DetailPeriksaActivity.STATUS to it.status
                )
            }
            adapter = recyclerViewAdapter
        }
        presenter.getHistory()
    }

    override fun showHistory(data: ListPeriksaResponse) {
        if(data.periksa.isEmpty()){
            rv_history.visibility = View.GONE
            data_empty.visibility = View.VISIBLE
        }else{
            rv_history.visibility = View.VISIBLE
            data_empty.visibility = View.GONE
            history.clear()
            history.addAll(data.periksa)
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }
}