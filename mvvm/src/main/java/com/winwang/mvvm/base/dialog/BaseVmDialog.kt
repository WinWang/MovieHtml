package com.winwang.mvvm.base.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.enums.ViewStatusEnum

/**
 *Created by WinWang on 2020/6/16
 *Description->Viewmodel请求的Dialog
 */
abstract class BaseVmDialog<VM : BaseViewModel> : BaseDialog() {

    protected val mViewModel: VM by viewModels {
        ViewModelProvider(this).get(viewModelClass())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        lifecycle.addObserver(mViewModel)
        initViewModel()
        initObserve()
        if (savedInstanceState == null) {
            initData()
        }
    }

    /**
     * 普通加载数据
     */
    open fun initData() {
        // Override if need
    }

    /**
     * 如果有需要初始化View数据
     */
    open fun initView() {
        // Override if need
    }

    open fun initObserve() {
        mViewModel.viewStatus.observe(viewLifecycleOwner, Observer {
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


    private fun initViewModel() {
        //通过lazy初始化
//        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    abstract fun viewModelClass(): Class<VM>

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
    }

}