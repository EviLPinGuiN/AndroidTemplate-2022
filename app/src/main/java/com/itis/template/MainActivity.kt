package com.itis.template

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.itis.template.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.extras?.let {

            }
        }
    }

    val permissions = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted->
        if (isGranted) {

        } else {

        }
    }

    val mPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        val isCameraGranted: Boolean? = it[Manifest.permission.CAMERA]
        if (isCameraGranted == true) {

        } else {

        }
    }

    val camera = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        it?.let {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // from binding
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.run {

        }

        launcher.launch(Intent(this, SecondActivity::class.java))


        permissions.launch(Manifest.permission.CAMERA)

        startActivityForResult(
            Intent(this, SecondActivity::class.java),
            REQUEST_CODE,
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.extras?.let {

                    }
                }
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    companion object {
        private const val REQUEST_CODE = 123
    }
}