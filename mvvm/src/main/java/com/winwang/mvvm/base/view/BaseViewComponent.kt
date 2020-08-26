package com.winwang.mvvm.base.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.lifecycle.*
import com.winwang.mvvm.base.lifecycle.LifeObserver
import com.winwang.mvvm.base.viewmodel.BaseViewModel

/**
 *Created by WinWang on 2020/8/25
 *Description->
 */
abstract class BaseViewComponent<VM : BaseViewModel> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(
        context,
        attrs,
        defStyleAttr
    ), LifeObserver {

    protected lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var viewModelStoreOwner: ViewModelStoreOwner

    protected lateinit var mViewModel: VM

    init {

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
//        lifecycleOwner = this.findViewTreeLifecycleOwner()!!
//        viewModelStoreOwner = this.findViewTreeViewModelStoreOwner()!!
        initView()
        initViewModel()
        initObserve()
        initData()
    }


    /**
     * 初始化观察者
     */
    open fun initObserve() {

    }


    /**
     * 如果有需要初始化View数据
     */
    open fun initView() {
    }

    /**
     * 普通加载数据
     */
    open fun initData() {
    }

    private fun initViewModel() {
//        mViewModel = ViewModelProvider(viewModelStoreOwner).get(viewModelClass())
    }

    abstract fun viewModelClass(): Class<VM>


}