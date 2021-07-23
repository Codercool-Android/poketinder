package com.abeltarazona.poketinder.presentation.ui.fragments.intro

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.abeltarazona.poketinder.R
import com.abeltarazona.poketinder.databinding.FragmentMainIntroBinding
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseFragment


class MainIntroFragment :
    BaseFragment<FragmentMainIntroBinding>(FragmentMainIntroBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnYes.setOnClickListener {
            Toast.makeText(context, "PRESIONADO YES", Toast.LENGTH_SHORT).show()
        }

        binding.btnNo.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainIntroFragment_to_failIntroFragment)
        }

        binding.btnClose.setOnClickListener {
            activity?.finish()
        }
    }
}