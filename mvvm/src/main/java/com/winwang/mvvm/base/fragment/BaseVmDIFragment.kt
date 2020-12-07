package com.winwang.mvvm.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.enums.ViewStatusEnum
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 *Created by WinWang on 2020/6/8
 *Description->
 */
@Deprecated(message = "不需要单独使用了，通过基类的isDIViewModel来控制注入方式，不使用多个基类")
abstract class BaseVmDIFragment<VM : BaseViewModel> : BaseFragment() {

    val mViewModel: VM by lazy {
        //koin 注入
        val clazz =
            this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<VM>
        getViewModel<VM>(clazz = clazz)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViewModel()
        lifecycle.addObserver(mViewModel)
        initView()
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


    fun initViewModel() {

        //传统构造方式
        //val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        //mViewModel = ViewModelProvider(this).get<T>(types[0] as Class<T>)

        //koin 注入
        // val clazz = this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<VM>
        // mViewModel = getViewModel<VM>(clazz)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
    }


}