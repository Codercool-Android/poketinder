package com.abeltarazona.poketinder.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.abeltarazona.poketinder.MainActivity
import com.abeltarazona.poketinder.data.User
import com.abeltarazona.poketinder.databinding.ActivityLoginBinding
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.google.gson.Gson
import org.json.JSONException

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
                val token: String? = result?.accessToken?.token
                // Call Graph API

                val request: GraphRequest = GraphRequest.newMeRequest(
                    result?.accessToken
                ) { obj, _ ->
                    try {

                        val idUser = obj.getString("id")
                        val name = obj.getString("name")
                        var email = ""

                        if (obj.has("email")) {
                            email = obj.getString("email")
                        }

                        val user = User(name = name, id = idUser, email = email)

                        val intent = Intent(applicationContext, ProfileActivity::class.java)
                        intent.putExtra("user", user)
                        startActivity(intent)



                    } catch (e: JSONException) {
                        Log.d("Codercool", e.message.toString())
                    }
                }

                val parameters = Bundle()
                parameters.putString("fields", "email, name")
                request.parameters = parameters
                request.executeAsync()

            }

            override fun onCancel() {
                Toast.makeText(applicationContext, "Para poder continuar usando la app, debe iniciar sesión con Facebook", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(applicationContext, error?.message, Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }



}