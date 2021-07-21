package com.abeltarazona.poketinder.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by AbelTarazona on 20/07/2021
 */
abstract class BaseFragment<D : ViewDataBinding> : Fragment() {

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    protected abstract fun initView()

    lateinit var mBinding: D

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)

        initView()

        return mBinding.root
    }

}