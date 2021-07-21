package com.abeltarazona.poketinder.presentation.ui.fragments.intro

import com.abeltarazona.poketinder.R
import com.abeltarazona.poketinder.databinding.FragmentMainIntroBinding
import com.abeltarazona.poketinder.presentation.ui.fragments.BaseFragment


class MainIntroFragment : BaseFragment<FragmentMainIntroBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_main_intro

    override fun initView() {
        mBinding.tvTest.text = "Hola probando"
    }

}