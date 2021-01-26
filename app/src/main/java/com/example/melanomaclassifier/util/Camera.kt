package com.example.melanomaclassifier.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File
import java.io.IOException

class Camera private constructor(builder: Builder) {

    val APP_TAG = "Camera"

    private val activity: Activity?
    private val context: Context?
    private var photoFileName = "image.jpg"
    var photoFile: File? = null
        private set
    private val target: ImageView?
    private var cameraBitmapPath: String? = null

    // by this point we have the camera photo on disk
    //Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getPath());
    // RESIZE BITMAP, see section below
    // Load the taken image into a preview
    val bitmap: Bitmap?
        @Throws(IOException::class)
        get() {
            var takenImage: Bitmap? = null
            photoFile?.let {
                takenImage = ImageUtils.decodeSampledBitmapFromFile(photoFile!!, 400, 400)
            }
            return takenImage
        }

    // Returns true if external storage for photos is available
    private val isExternalStorageAvailable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return state == Environment.MEDIA_MOUNTED
        }

    val packageName: String
        get() = activity!!.packageName

    init {
        activity = builder.activity
        context = builder.context
        photoFileName = builder.photoFileName!!
        target = builder.target
    }

    class Builder {
        internal var context: Context? = null
        internal var activity: Activity? = null
        internal var photoFileName: String? = null
        internal var target: ImageView? = null

        fun build(activity: Activity): Camera {
            this.activity = activity
            context = activity.applicationContext
            return Camera(this)
        }

        fun setPhotoFileName(photoFileName: String): Builder {
            this.photoFileName = photoFileName
            return this
        }

        fun setTarget(target: ImageView): Builder {
            this.target = target
            return this
        }
    }

    /**
     * Called from activity
     */
    @RequiresPermission(Manifest.permission.CAMERA)
    fun takeCamera() {
        // create Intent to take a picture and return control to the calling application
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Create a File reference to access to future access
        photoFile = getPhotoFileUri(photoFileName)

        cameraBitmapPath = photoFile!!.absolutePath

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        val fileProvider = FileProvider.getUriForFile(activity!!, packageName, photoFile!!)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(activity.packageManager) != null) {
            // Start the image capture intent to take photo
            //startActivityForResult(intent, REQUEST_CODE_CAPTURE_IMAGE);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile))
            } else {
                val file2 = File(Uri.fromFile(photoFile).path)
                val photoUri = FileProvider.getUriForFile(
                    activity,
                    packageName, file2
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            }
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            activity.startActivityForResult(intent, REQUEST_CODE_CAPTURE_IMAGE)
        }
    }

    /**
     * Called from fragment
     */
    @RequiresPermission(Manifest.permission.CAMERA)
    fun takeCamera(fragment: Fragment) {
        // create Intent to take a picture and return control to the calling application
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Create a File reference to access to future access
        photoFile = getPhotoFileUri(photoFileName)

        cameraBitmapPath = photoFile!!.absolutePath

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        val fileProvider = FileProvider.getUriForFile(activity!!, packageName, photoFile!!)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(activity.packageManager) != null) {
            // Start the image capture intent to take photo
            //startActivityForResult(intent, REQUEST_CODE_CAPTURE_IMAGE);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile))
            } else {
                val file2 = File(Uri.fromFile(photoFile).path)
                val photoUri = FileProvider.getUriForFile(
                    activity,
                    packageName, file2
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            }
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            fragment.startActivityForResult(intent, REQUEST_CODE_CAPTURE_IMAGE)
        }
    }

    // Returns the File for a photo stored on disk given the fileName
    fun getPhotoFileUri(fileName: String?): File? {
        // Only continue if the SD Question is mounted
        if (isExternalStorageAvailable) {
            // Get safe storage directory for photos
            // Use `getExternalFilesDir` on Context to access package-specific directories.
            // This way, we don't need to request external read/write runtime permissions.
            val mediaStorageDir = File(
                activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG
            )

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                Log.d(APP_TAG, "failed to create directory")
            }

            // Return the file target for the photo based on filename

            return File(mediaStorageDir.path + File.separator + fileName)
        }
        return null
    }


    @Throws(IOException::class)
    fun onImageCaptured() {
        if (target == null) {
            throw RuntimeException("Please provide target image view")
        }
        target.setImageBitmap(bitmap)
    }

    /**
     * Deletes the saved camera image
     */
    fun deleteImage() {
        if (cameraBitmapPath != null) {
            val image = File(cameraBitmapPath)
            if (image.exists()) {
                image.delete()
            }
        }
    }

    private fun onError() {
        Toast.makeText(activity, "Field", Toast.LENGTH_SHORT).show()
    }

    companion object {
        val REQUEST_CODE_CAPTURE_IMAGE = 1990
    }
}
