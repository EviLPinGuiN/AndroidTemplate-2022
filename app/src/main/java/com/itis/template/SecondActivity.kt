package com.itis.template

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra("", "")
        })
        finish()
    }
}