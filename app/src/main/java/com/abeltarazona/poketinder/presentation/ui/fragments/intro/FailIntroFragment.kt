package com.abeltarazona.poketinder.presentation.ui.fragments.intro

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.abeltarazona.poketinder.R
import com.abeltarazona.poketinder.databinding.FragmentFailIntroBinding
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseFragment
import com.bumptech.glide.Glide


class FailIntroFragment :
    BaseFragment<FragmentFailIntroBinding>(FragmentFailIntroBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide
            .with(this)
            .load("https://i.imgur.com/5Qs5JcN.png")
            .into(binding.imageView4)

        binding.btnPrepare.setOnClickListener {
            Navigation
                .findNavController(it)
                .navigate(R.id.action_failIntroFragment_to_webIntroFragment)
        }


    }

}