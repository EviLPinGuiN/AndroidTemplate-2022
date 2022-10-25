package com.itis.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.itis.template.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // from binding
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val controller =
            (supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment)
                .navController


        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.homeFragment),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        findViewById<Toolbar>(androidx.appcompat.R.id.action_bar)
            .setupWithNavController(controller, appBarConfiguration)

        binding?.run {
            bnvMain.setupWithNavController(controller)
//            bnvMain.setOnItemSelectedListener {
//                when (it.itemId) {
//                    R.id.main_profile -> {
////                        navigateTo(ProfileFragment())
//                        true
//                    }
//                    R.id.main_home -> {
////                        controller.navigate(R.id.action_profileFragment_to_homeFragment)
//                        true
//                    }
//                    R.id.main_settings -> {
////                        navigateTo(ProfileFragment())
//                        true
//                    }
//                    else -> false
//                }
//            }
//            bnvMain.selectedItemId = R.id.main_profile
        }
    }

    override fun onBackPressed() {
        binding?.run {
            if (bnvMain.selectedItemId != R.id.settingFragment) {
                bnvMain.selectedItemId = R.id.settingFragment
            } else {
                super.onBackPressed()
            }
        }
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.host_fragment, fragment, fragment.javaClass.name)
            .commit()
    }

    companion object {

        private const val ARG_SCORE = "arg_score"
    }
}