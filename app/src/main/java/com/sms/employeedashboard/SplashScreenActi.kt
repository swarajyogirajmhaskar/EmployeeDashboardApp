package com.sms.employeedashboard

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({

            val isLoggedIn = preferences.getBoolean("isLoggedIn", false)

            val nextScreen =
                if (isLoggedIn) MainActivity::class.java
                else LoginActivity::class.java

            startActivity(Intent(this, nextScreen))
            finish()

        }, 1000)
    }
}