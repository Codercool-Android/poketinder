package com.abeltarazona.poketinder.presentation.ui.fragments.intro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.abeltarazona.poketinder.R
import com.abeltarazona.poketinder.databinding.FragmentMainIntroBinding
import com.abeltarazona.poketinder.presentation.presenters.implementation.MainIntroImpl
import com.abeltarazona.poketinder.presentation.presenters.interfaces.MainIntroPresenter
import com.abeltarazona.poketinder.presentation.ui.activities.LoginActivity
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseFragment
import com.abeltarazona.poketinder.presentation.utils.SharedPreferenceUtil
import com.zygne.zygnearchitecture.domain.executor.implementation.ThreadExecutor
import com.zygne.zygnearchitecture.threads.AndroidThread


class MainIntroFragment :
    BaseFragment<FragmentMainIntroBinding>(FragmentMainIntroBinding::inflate), 
    MainIntroPresenter.Callback {

    private lateinit var presenter: MainIntroPresenter

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreference(requireContext())
        }

        presenter = MainIntroImpl(ThreadExecutor.getInstance(),
            AndroidThread.getInstance(), this)

        binding.btnYes.setOnClickListener {
            //presenter.convertToWelcomeMessage("Abel Tarazona")
            sharedPreferenceUtil.saveIntroShow()
            startActivity(Intent(context, LoginActivity::class.java))
        }

        binding.btnNo.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainIntroFragment_to_failIntroFragment)
        }

        binding.btnClose.setOnClickListener {
            activity?.finish()
        }
    }

    override fun onConvertToWelcomeMessageDone(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}