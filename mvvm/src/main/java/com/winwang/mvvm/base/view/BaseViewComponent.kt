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
    attrs: AttributeSet? = null
) :
    FrameLayout(
        context,
        attrs
    ), LifeObserver {

    protected lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var viewModelStoreOwner: ViewModelStoreOwner
    open var mContext: Context = context

    protected lateinit var mViewModel: VM

    init {
        lifecycleOwner = this.findViewTreeLifecycleOwner()!!
        viewModelStoreOwner = this.findViewTreeViewModelStoreOwner()!!

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        lifecycleOwner = getLifeOwner()!!
        viewModelStoreOwner = getViewModelOwner()!!
        initView()
        initViewModel()
        initObserve()
        initData()

    }


    fun getLifeOwner(): LifecycleOwner? {
        if (mContext != null) {
            if (mContext is LifecycleOwner) {
                return mContext as LifecycleOwner
            } else {
                return null
            }
        } else {
            return null
        }
    }

    fun getViewModelOwner(): ViewModelStoreOwner? {
        if (mContext != null) {
            if (mContext is LifecycleOwner) {
                return mContext as ViewModelStoreOwner
            } else {
                return null
            }
        } else {
            return null
        }
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
        mViewModel = ViewModelProvider(viewModelStoreOwner).get(viewModelClass())
    }

    abstract fun viewModelClass(): Class<VM>


}