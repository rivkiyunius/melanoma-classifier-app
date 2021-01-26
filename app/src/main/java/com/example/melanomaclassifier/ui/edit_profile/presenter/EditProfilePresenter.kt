package com.example.melanomaclassifier.ui.edit_profile.presenter

import com.example.melanomaclassifier.data.network.request.EditProfileRequest
import com.example.melanomaclassifier.ui.base.presenter.BasePresenter
import com.example.melanomaclassifier.ui.edit_profile.interactor.EditProfileMvpInteractor
import com.example.melanomaclassifier.ui.edit_profile.view.EditProfileMvpView
import com.example.melanomaclassifier.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.io.File
import javax.inject.Inject

class EditProfilePresenter<V : EditProfileMvpView, I : EditProfileMvpInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), EditProfileMvpPresenter<V, I> {
    override fun update(
        nama: String,
        jenisKelamin: String,
        noTelepon: String,
        alamat: String,
        file: File?,
        tanggalLahir: String,
        ttl: String,
        riwayatPenyakit: String,
        umur: Int
    ) {
        val request = EditProfileRequest()
        request.nama = nama
        request.jenisKelamin = jenisKelamin
        request.noTelepon = noTelepon
        request.alamat = alamat
        request.file = file
        request.riwayatPenyakit = riwayatPenyakit
        request.tanggalLahir = tanggalLahir
        request.tempatTanggalLahir = ttl
        request.umur = umur

        interactor?.let {
            compositeDisposable.add(
                it.postUpdate(request)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .doOnSubscribe { getView()?.showLoading() }
                    .doOnTerminate { getView()?.hideLoading() }
                    .subscribe({ response ->
                        if (response.isSuccessfull()) {
                            getView()?.onSuccess()
                        }
                    }, { e ->
                        when (e) {
                            is HttpException -> handleApiError(e)
                            else -> handleGenericError(e.localizedMessage)
                        }
                    })
            )
        }
    }

    override fun loadData() {
        interactor?.let {
            compositeDisposable.add(
                it.loadDataUser()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .doOnSubscribe { getView()?.showLoading() }
                    .doOnTerminate { getView()?.hideLoading() }
                    .subscribe({ response ->
                        if (response.isSuccessfull()) {
                            getView()?.loadData(response.result)
                        }
                    }, { e ->
                        when (e) {
                            is HttpException -> handleApiError(e)
                            else -> handleGenericError(e.localizedMessage)
                        }
                    })
            )
        }
    }
}