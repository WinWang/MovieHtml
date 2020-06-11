package com.winwang.moviehtml.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

/**
 *Created by WinWang on 2020/6/8
 *Description->
 */
abstract class BaseVmActivity<VM : BaseViewModel> : BaseActivity() {

    protected open lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initViewModel()
        if (savedInstanceState == null) {
            initData()
        }
    }

    abstract fun initData()

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    open fun initView(){

    }

    protected abstract fun viewModelClass(): Class<VM>

}