package com.mustafayosri.gadsleaderboard.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.mustafayosri.gadsleaderboard.MainActivity
import com.mustafayosri.gadsleaderboard.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity( Intent(this, MainActivity::class.java))
            finish()
        }, 1000)

    }
}
