package com.abeltarazona.poketinder.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.abeltarazona.poketinder.MainActivity
import com.abeltarazona.poketinder.data.User
import com.abeltarazona.poketinder.databinding.ActivitySplashBinding
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseActivity
import com.abeltarazona.poketinder.presentation.utils.SharedPreferenceUtil

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreference(this)
        }

        Handler(Looper.getMainLooper()).postDelayed(
            {
                // Evaluar si mostrar intro o no
                val isIntroAvailable = sharedPreferenceUtil.getIntroShow()
                if (!isIntroAvailable) {
                    startActivity(Intent(this, IntroActivity::class.java))
                } else {
                    // Evaluar si mostrar el login o pantalla principal
                    val user: User? = sharedPreferenceUtil.getUser()
                    if (user != null) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("user", user)
                        startActivity(intent)
                    } else {
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                }
                finish()
            },
            3000 // value in milliseconds
        )




    }

}