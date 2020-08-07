package com.winwang.mvvm.base.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.enums.ViewStatusEnum

/**
 *Created by WinWang on 2020/8/5
 *Description->这是依赖注入的类-》viewmodel通过依赖注入，如果不想依赖注入的话可以通过BaseVmActivity
 */
abstract class BaseVmDIActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        getViewModel()
        initObserve()
        // 因为Activity恢复后savedInstanceState不为null，
        // 重新恢复后会自动从ViewModel中的LiveData恢复数据，
        // 不需要重新初始化数据。
        if (savedInstanceState == null) {
            initData()
            loadNet()
        }
    }

    abstract fun getViewModel(): BaseViewModel

    open fun initView() {

    }

    open fun initData() {

    }

    open fun initObserve() {
        getViewModel().viewStatus.observe(this, Observer {
            when (it) {
                ViewStatusEnum.SUCCESS -> {
                    showSuccess()
                }

                ViewStatusEnum.ERROR -> {
                    showError()
                }

                ViewStatusEnum.EMPTY -> {
                    showEmpty()
                }

                ViewStatusEnum.NETWORKERROR -> {
                    showTimeOut()
                }

            }
        })
    }


}