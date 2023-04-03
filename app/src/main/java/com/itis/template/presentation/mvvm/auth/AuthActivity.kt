package com.itis.template.presentation.mvvm.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.itis.template.App
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: AuthViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        App.appComponent.plusAuthComponent()
//            .setCityId(intent.extras?.getInt("arg_id", -1) ?: -1)
//            .build()
//            .inject(this)
        super.onCreate(savedInstanceState)
    }
}