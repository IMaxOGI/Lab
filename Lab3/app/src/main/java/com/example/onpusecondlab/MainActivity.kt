package com.example.onpusecondlab

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.onpusecondlab.databinding.ActivityMainBinding
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var fileUri: Uri? = null

    private val getCameraImage =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { result ->
            if (result) {
                Log.i("INFO", "Picture captured")
                fileUri?.let { uri ->
                    binding.iv.setImageURI(uri)
                }
            } else {
                Log.i("INFO", "ERROR")
            }
        }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.i("INFO", "Camera permissions granted")
            Toast.makeText(
                baseContext,
                "We cannot do photos without your permission!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Log.i("INFO", "Camera permissions not granted")
            Toast.makeText(
                baseContext,
                "We cannot do photos without your permission!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val takePicture = binding.takePictureButton
        val sendPicture = binding.sendButton

        binding.apply {
            takePicture.setOnClickListener {
                grantCameraPermission()
                fileUri = getFileUri()
                getCameraImage.launch(fileUri)
            }
            sendPicture.setOnClickListener {
                sendMailIntent()
            }
        }
    }

    // check for camera permission and if not granted yet, try to get permission
    private fun grantCameraPermission() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                baseContext,
                Manifest.permission.CAMERA
            ) -> {
                Log.i("TAG", "Camera permissions granted already")
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun getFileUri(): Uri {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.UK).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            imageFileName,
            ".jpg",
            storageDir
        )
        return FileProvider.getUriForFile(this, "com.example.onpusecondlab.fileprovider", image)
    }

    private fun sendMailIntent() {
        fileUri?.let {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.apply {
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                setDataAndType(it, contentResolver.getType(it))
                putExtra(Intent.EXTRA_STREAM, it)
                putExtra(Intent.EXTRA_EMAIL, arrayOf("hodovychenko.labs@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "КПП Аи-194 Оганесян М. А.")
            }
            startActivity(Intent.createChooser(emailIntent, "Send with..."))
        }
    }
}
