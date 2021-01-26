package com.example.melanomaclassifier.ui.edit_profile.presenter

import com.example.melanomaclassifier.ui.base.presenter.MvpPresenter
import com.example.melanomaclassifier.ui.edit_profile.interactor.EditProfileMvpInteractor
import com.example.melanomaclassifier.ui.edit_profile.view.EditProfileMvpView
import java.io.File

interface EditProfileMvpPresenter<V : EditProfileMvpView, I : EditProfileMvpInteractor> :
    MvpPresenter<V, I> {
    fun update(
        nama: String,
        jenisKelamin: String,
        noTelepon: String,
        alamat: String,
        file: File?,
        tanggalLahir: String,
        ttl: String,
        riwayatPenyakit: String,
        umur: Int
    )

    fun loadData()
}