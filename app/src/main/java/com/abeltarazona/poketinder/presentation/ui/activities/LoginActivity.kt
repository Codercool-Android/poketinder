package com.abeltarazona.poketinder.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.abeltarazona.poketinder.databinding.ActivityLoginBinding
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    lateinit var callbackManager: CallbackManager

    private val EMAIL = "email"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.layBackButton.btnBackClose.setOnClickListener {
            finish()
        }

        callbackManager = CallbackManager.Factory.create()

        val permissions: MutableList<String> = mutableListOf()
        permissions.add(EMAIL)

        binding.loginButton.setPermissions(permissions)

        binding.loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.d("Codercool", "onSuccess: ")

                Log.d("Codercool", result?.accessToken?.token ?: "---")
            }

            override fun onCancel() {
                Log.d("Codercool", "onCancel: ")
            }

            override fun onError(error: FacebookException?) {
                Log.d("Codercool", "onError: ${error?.message}")
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }



}