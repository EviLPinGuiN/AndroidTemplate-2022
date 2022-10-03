package com.itis.template

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.itis.template.databinding.ActivityMainBinding
import kotlin.random.Random
import android.graphics.Color as AndroidColor

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // from binding
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.run {

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProfileFragment(), "TAg")
                .commit()
        }
    }

    companion object {

        private const val ARG_SCORE = "arg_score"
    }
}