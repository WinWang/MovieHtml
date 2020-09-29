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
abstract class BaseVmFragment<VM : BaseViewModel> : BaseFragment() {

    protected val mViewModel: VM by lazy {
        ViewModelProvider(this).get(viewModelClass())
    }
    private var lazyLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
        lifecycle.addObserver(mViewModel)
        // 因为Fragment恢复后savedInstanceState不为null，
        // 重新恢复后会自动从ViewModel中的LiveData恢复数据，
        // 不需要重新初始化数据。
        initObserve()
        if (savedInstanceState == null) {
            initData()
        }
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


    /**
     * 通过by lazy来初始化，不在需要单独初始化
     */
    private fun initViewModel() {
//        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    abstract fun viewModelClass(): Class<VM>

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
    }


}