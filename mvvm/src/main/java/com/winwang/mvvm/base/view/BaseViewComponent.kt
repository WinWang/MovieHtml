package com.winwang.mvvm.base.view

import android.content.Context
import android.opengl.Visibility
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.*
import com.blankj.utilcode.util.LogUtils
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


    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    open fun init() {
        lifecycleOwner = this.findViewTreeLifecycleOwner()!!
        viewModelStoreOwner = this.findViewTreeViewModelStoreOwner()!!
        lifecycleOwner.lifecycle.addObserver(this)
        initView()
        initViewModel()
        initObserve()
        initData()
    }


    fun getLifeOwner(): LifecycleOwner? {
        if (mContext is LifecycleOwner) {
            return mContext as LifecycleOwner
        } else {
            return null
        }
    }

    fun getViewModelOwner(): ViewModelStoreOwner? {
        if (mContext is LifecycleOwner) {
            return mContext as ViewModelStoreOwner
        } else {
            return null
        }
    }


    override fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        LogUtils.d("onCreate>>>>>>>>>>")
    }

    override fun onPause(owner: LifecycleOwner) {
        LogUtils.d("onPause>>>>>>>>>>")
    }

    override fun onResume(owner: LifecycleOwner) {
        LogUtils.d("onResume>>>>>>>>>>")
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