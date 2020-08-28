package com.winwang.mvvm.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.enums.ViewStatusEnum

/**
 *Created by WinWang on 2020/6/8
 *Description->
 */
abstract class BaseVmDIFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
        // 因为Fragment恢复后savedInstanceState不为null，
        // 重新恢复后会自动从ViewModel中的LiveData恢复数据，
        // 不需要重新初始化数据。
        initObserve()
        if (savedInstanceState == null) {
            initData()
        }
    }

    open fun initObserve() {
        initViewModel().viewStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                ViewStatusEnum.SUCCESS -> {
                    showSuccess()
                    hideRefresh()
                }

                ViewStatusEnum.ERROR -> {
                    showError()
                    hideRefresh()
                }

                ViewStatusEnum.EMPTY -> {
                    showEmpty()
                    hideRefresh()
                }

                ViewStatusEnum.NETWORKERROR -> {
                    showTimeOut()
                    hideRefresh()
                }

            }
        })
    }

    /**
     * 如果有需要初始化View数据
     */
    open fun initView() {
        // Override if need
    }

    /**
     * 普通加载数据
     */
    open fun initData() {
        // Override if need
    }


    abstract fun initViewModel(): BaseViewModel


}