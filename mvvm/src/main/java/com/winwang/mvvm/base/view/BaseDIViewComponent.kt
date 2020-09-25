package com.winwang.mvvm.base.view

import android.content.Context
import android.opengl.Visibility
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.*
import com.blankj.utilcode.util.LogUtils
import com.winwang.mvvm.R
import com.winwang.mvvm.base.App
import com.winwang.mvvm.base.lifecycle.LifeObserver
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.ext.showToast
import org.greenrobot.eventbus.EventBus
import org.koin.core.KoinComponent

/**
 *Created by WinWang on 2020/8/25
 *Description->
 */
abstract class BaseDIViewComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    FrameLayout(
        context,
        attrs
    ), LifeObserver, KoinComponent {

    init {
        if (getLayoutId() > -1) {
            inflate(context, getLayoutId(), this)
        }
    }


    protected lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var viewModelStoreOwner: ViewModelStoreOwner
    open var mContext: Context = context

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    open fun init() {
        lifecycleOwner = this.findViewTreeLifecycleOwner()!!
        viewModelStoreOwner = this.findViewTreeViewModelStoreOwner()!!
        lifecycleOwner.lifecycle.addObserver(this)
        LogUtils.d("viewInit>>>>>>>>>>")

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
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        LogUtils.d("onCreate>>>>>>>>>>")
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        initView()
        initObserve()
        initData()
    }

    override fun onPause(owner: LifecycleOwner) {
        LogUtils.d("onPause>>>>>>>>>>")
    }

    override fun onResume(owner: LifecycleOwner) {
        LogUtils.d("onResume>>>>>>>>>>")
    }

    protected open fun useEventBus(): Boolean = false

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

    open fun getLayoutId(): Int = -1

    open fun showToast(toastMessage: String) {
        App.instance.showToast(
            toastMessage
        )
    }


}