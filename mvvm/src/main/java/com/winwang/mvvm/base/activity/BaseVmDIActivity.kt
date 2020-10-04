package com.winwang.mvvm.base.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.enums.ViewStatusEnum
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

/**
 *Created by WinWang on 2020/8/5
 *Description->这是依赖注入的类-》viewmodel通过依赖注入，如果不想依赖注入的话可以通过BaseVmActivity
 */
abstract class BaseVmDIActivity<VM : BaseViewModel> : BaseActivity() {

    val mViewModel: VM by lazy {
        //koin 注入
        val clazz =
            this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<VM>
        getViewModel<VM>(clazz = clazz)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(mViewModel)
        initView()
//        getViewModel()
        initObserve()
        // 因为Activity恢复后savedInstanceState不为null，
        // 重新恢复后会自动从ViewModel中的LiveData恢复数据，
        // 不需要重新初始化数据。
        if (savedInstanceState == null) {
            initData()
            loadNet()
        }
    }

//    abstract fun getViewModel(): BaseViewModel

    open fun initView() {

    }

    open fun initData() {

    }

    open fun initObserve() {
        mViewModel.viewStatus.observe(this, Observer {
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

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
    }


}