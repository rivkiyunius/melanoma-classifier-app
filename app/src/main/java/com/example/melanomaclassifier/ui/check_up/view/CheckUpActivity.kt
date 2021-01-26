package com.example.melanomaclassifier.ui.check_up.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.data.network.response.DokterModel
import com.example.melanomaclassifier.ui.base.view.BaseActivity
import com.example.melanomaclassifier.ui.check_up.interactor.CheckUpMvpInteractor
import com.example.melanomaclassifier.ui.check_up.presenter.CheckUpMvpPresenter
import com.example.melanomaclassifier.ui.dokter_picker.ui.DokterPickerActivity
import com.example.melanomaclassifier.ui.main.view.MainActivity
import com.example.melanomaclassifier.util.Camera
import com.example.melanomaclassifier.util.FileUtil
import com.example.melanomaclassifier.util.loadImage
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tbruyelle.rxpermissions2.RxPermissions
import id.zelory.compressor.Compressor
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_check_up.*
import kotlinx.android.synthetic.main.app_bar.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.File
import java.io.IOException
import javax.inject.Inject

class CheckUpActivity : BaseActivity(), CheckUpMvpView {

    companion object {
        const val RC_PICK_IMAGE = 1
    }

    @Inject
    lateinit var presenter: CheckUpMvpPresenter<CheckUpMvpView, CheckUpMvpInteractor>

    private lateinit var camera: Camera
    private lateinit var compositeDisposable: CompositeDisposable
    private var pickedImageFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_up)

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.title = "Pemeriksaan"

        presenter.onAttach(this)

        compositeDisposable = CompositeDisposable()

        setupViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RC_PICK_IMAGE -> {
                    if (data == null) {
                        toast("Gagal menerima gambar")
                        return
                    }
                    try {
                        val actualImage = FileUtil.from(this, data.data)
                        pickedImageFile = compressImage(actualImage)
                        pickedImageFile?.let {
                            img_take_photo.loadImage(it)
                        }
                    } catch (e: IOException) {
                        Toast.makeText(
                            this@CheckUpActivity,
                            "Gagal mengambil gambar",
                            Toast.LENGTH_SHORT
                        ).show()
                        e.printStackTrace()
                    }
                }

                Camera.REQUEST_CODE_CAPTURE_IMAGE -> {
                    val actualImage = camera.photoFile
                    actualImage?.let { ai ->
                        pickedImageFile = compressImage(ai)
                        pickedImageFile?.let {
                            img_take_photo.loadImage(it)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun setupViews() {

        val intent = intent.getParcelableExtra<DokterModel>("dokter")

        editTextPilihDokter.setOnClickListener {
            startActivity<DokterPickerActivity>()
        }

        editTextPilihDokter.setText(intent?.nama)

        img_take_photo.setOnClickListener {
            settingCamera()
        }

        btn_periksa.setOnClickListener {
            val keluhan = edt_keluhan.text.toString()
            presenter.checkUp(pickedImageFile, keluhan, intent?.id.toString())
        }
    }

    @SuppressLint("MissingPermission")
    private fun showCamera() {
        camera.takeCamera()
    }

    private fun chooseImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }

    @SuppressLint("MissingPermission")
    private fun settingCamera() {
        camera = Camera.Builder()
            .setPhotoFileName("mel_" + System.currentTimeMillis())
            .build(this)

        compositeDisposable.add(
            RxPermissions(this)
                .request(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .subscribe { granted: Boolean ->
                    if (granted) {
                        val dialog = BottomSheetDialog(this)
                        val view = layoutInflater.inflate(R.layout.dialog_pick_image, null)
                        val cameraOptionView: View = view.findViewById(R.id.view_take_photo)
                        cameraOptionView.setOnClickListener {
                            dialog.cancel()
                            showCamera()
                        }
                        val gallery: View = view.findViewById(R.id.view_pick_photo)
                        gallery.setOnClickListener {
                            dialog.cancel()
                            chooseImage()
                        }

                        dialog.setContentView(view)
                        dialog.show()
                    } else {
                        toast("Tidak bisa mengakses kamera")
                    }
                }
        )
    }

    private fun compressImage(actualImage: File): File? {
        var result: File? = null
        try {
            result = Compressor(this)
                .setMaxWidth(640)
                .setMaxHeight(480)
                .setQuality(75)
                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                .setDestinationDirectoryPath(
                    Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                    ).absolutePath
                )
                .compressToFile(actualImage)

        } catch (e: IOException) {
            e.printStackTrace()
            e.message?.let { toast(it) }
        }
        return result

    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    override fun redirectToHome() {
        startActivity<MainActivity>()
    }
}