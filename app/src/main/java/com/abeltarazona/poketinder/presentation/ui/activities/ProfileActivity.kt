package com.abeltarazona.poketinder.presentation.ui.activities

import android.os.Bundle
import com.abeltarazona.poketinder.data.User
import com.abeltarazona.poketinder.databinding.ActivityProfileBinding
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseActivity
import com.bumptech.glide.Glide

class ProfileActivity : BaseActivity<ActivityProfileBinding>(ActivityProfileBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = intent.getSerializableExtra("user") as User

        binding.tvName.text = user.name
        binding.tvEmail.text = user.email

        Glide
            .with(this)
            .load(user.getImage())
            .into(binding.profileImage)
    }
}