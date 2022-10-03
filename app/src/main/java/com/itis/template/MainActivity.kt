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

            savedInstanceState?.getInt(ARG_SCORE)?.let {
                score = it
                tvHelloMessage1.text = "score: $it"
            }

            tvHelloMessage.setOnClickListener { btn ->
                score++
                tvHelloMessage1.text = "score: $score"
            }

            image.setOnClickListener {
                sendImplictIntent()
            }
        }
    }

    private fun sendImplictIntent() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$+78005553535®")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }

        val chooserIntent = Intent.createChooser(
            intent,
            "HIHI I am murlock"
        )

        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivity(chooserIntent)
        }

    }

    companion object {

        private const val ARG_SCORE = "arg_score"
    }
}