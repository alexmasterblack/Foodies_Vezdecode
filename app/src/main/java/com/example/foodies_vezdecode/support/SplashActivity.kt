package com.example.foodies_vezdecode.support

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.foodies_vezdecode.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setSplashScreen()
    }

    private fun setSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },
            splashScreenDuration
        )
    }

    private fun getSplashScreenDuration() = 2000L
}