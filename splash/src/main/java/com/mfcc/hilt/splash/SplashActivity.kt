package com.mfcc.hilt.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.mfcc.hilt.core.base.BaseInjectableActivity
import com.mfcc.hilt.splash.databinding.ActivitySplashBinding
import com.mfcc.hilt.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseInjectableActivity<SplashViewModel, ActivitySplashBinding>() {

    override val layoutResource: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            viewModel = viewModel
        }

        lifecycleScope.launch {
            delay(2000)
            navigateToMovies()
        }

    }

    private fun navigateToMovies() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mfcc://movies"))
        startActivity(intent)
        finish()
    }

}