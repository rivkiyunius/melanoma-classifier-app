package com.example.melanomaclassifier.ui.edit_profile.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import com.example.melanomaclassifier.BuildConfig
import com.example.melanomaclassifier.R
import com.example.melanomaclassifier.data.network.response.GetUserResponse
import com.example.melanomaclassifier.ui.base.view.BaseActivity
import com.example.melanomaclassifier.ui.edit_profile.interactor.EditProfileMvpInteractor
import com.example.melanomaclassifier.ui.edit_profile.presenter.EditProfileMvpPresenter
import com.example.melanomaclassifier.ui.main.view.MainActivity
import com.example.melanomaclassifier.util.Camera
import com.example.melanomaclassifier.util.FileUtil
import com.example.melanomaclassifier.util.loadImage
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tbruyelle.rxpermissions2.RxPermissions
import id.zelory.compressor.Compressor
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.joda.time.DateTime
import org.joda.time.Period
import java.io.File
import java.io.IOException
import java.util.*
import javax.inject.Inject

class EditProfileActivity : BaseActivity(), EditProfileMvpView {

    companion object {
        const val RC_PICK_IMAGE = 1
    }

    @Inject
    lateinit var presenter: EditProfileMvpPresenter<EditProfileMvpView, EditProfileMvpInteractor>

    private lateinit var camera: Camera
    private lateinit var compositeDisposable: CompositeDisposable
    private var pickedImageFile: File? = null
    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDate: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        compositeDisposable = CompositeDisposable()

        setup()
    }

    private fun setup() {
        presenter.onAttach(this)

        editTextTanggalLahir.onClick {
            showDialogDatePicker()
        }

        imageViewProfile.onClick {
            settingCamera()
        }

        buttonSave.setOnClickListener {
            val nama = editTextNama.text.toString()
            val gender = if (genderRadioButtonGroup.checkedRadioButtonId != 0) {
                if (genderRadioButtonGroup.checkedRadioButtonId == R.id.maleRadioButton) "laki_laki" else "perempuan"
            } else {
                ""
            }
            val noTelepon = editTextPhoneNumber.text.toString()
            val alamat = editTextAlamat.text.toString()
            val riwayatPenyakit = editTextRiwayatPenyakit.text.toString()
            val tanggalLahir = editTextTanggalLahir.text.toString()
            val tanggalLahirFormatted =
                tanggalLahir.split("/")[2] + "-" + tanggalLahir.split("/")[1] + "-" + tanggalLahir.split(
                    "/"
                )[0]

            val tempatTanggalLahir = editTextTempatTanggalLahir.text.toString()
            val umur = textViewUsia.text.toString()

            presenter.update(
                nama,
                gender,
                noTelepon,
                alamat,
                pickedImageFile,
                tanggalLahirFormatted,
                tempatTanggalLahir,
                riwayatPenyakit,
                umur.toInt()
            )
        }

        presenter.loadData()
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
                            imageViewProfile.loadImage(it)
                        }
                    } catch (e: IOException) {
                        Toast.makeText(
                            this@EditProfileActivity,
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
                            imageViewProfile.loadImage(it)
                        }
                    }
                }
            }
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

    private fun showDialogDatePicker() {
        val currentDate = Calendar.getInstance()

        var dialog: AlertDialog? = null
        var selectedDate = ""
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_date_picker, null)
        val datePicker = dialogLayout.findViewById<DatePicker>(R.id.datePicker)
        datePicker.maxDate = Date().time
        datePicker.init(
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)
        ) { _, year, monthOfYear, dayOfMonth ->
            selectedDate =
                "${String.format("%02d", dayOfMonth)}/${String.format("%02d", monthOfYear)}/$year"
            mYear = year
            mMonth = monthOfYear
            mDate = dayOfMonth

            updateAgeField()
        }

        val cancelBtn = dialogLayout.findViewById<Button>(R.id.cancelBtn_datePicker)
        cancelBtn.onClick {
            dialog?.dismiss()
        }
        val saveBtn = dialogLayout.findViewById<Button>(R.id.saveBtn_datePicker)
        saveBtn.onClick {
            editTextTanggalLahir.setText(selectedDate)
            dialog?.dismiss()
        }

        builder.setView(dialogLayout)
        dialog = builder.show()
    }

    private fun updateAgeField() {
        val dobCalendar = Calendar.getInstance()
        dobCalendar.set(mYear, mMonth, mDate)
        val dobDateTime = DateTime(dobCalendar)
        val today = DateTime()

        val period = Period(dobDateTime, today)
        textViewUsia.text = period.years.toString()
    }

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String) {}

    override fun onSuccess() {
        startActivity<MainActivity>()
    }

    override fun loadData(data: GetUserResponse) {
        if (data.jk == null) {
            editTextNama.setText(data.nama)
        } else {
            imageViewProfile.loadImage(BuildConfig.BASE_URL_IMAGE + data.gambar)
            editTextNama.setText(data.nama)
            editTextPhoneNumber.setText(data.noHp)
            editTextAlamat.setText(data.alamat)
            data.jk.let {
                if(it == "laki_laki"){
                    maleRadioButton.isChecked = true
                }else{
                    femaleRadioButton.isChecked = true
                }
            }
            data.tanggalLahir.let {
                mYear = it.split("-")[0].toInt()
                mMonth = it.split("-")[1].toInt()
                mDate = it.split("-")[2].toInt()
                editTextTanggalLahir.setText("$mDate/$mMonth/$mYear")
            }
            editTextRiwayatPenyakit.setText(data.riwayatPenyakit)
            editTextTempatTanggalLahir.setText(data.tempatTanggalLahir)
            textViewUsia.text = data.umur.toString()
        }
    }
}