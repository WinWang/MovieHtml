package com.winwang.moviehtml.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.qmuiteam.qmui.widget.QMUITopBar
import com.winwang.moviehtml.R
import com.winwang.moviehtml.loadsir.EmptyCallback
import com.winwang.moviehtml.loadsir.ErrorCallback
import com.winwang.moviehtml.loadsir.LoadingCallback
import com.winwang.moviehtml.loadsir.TimeoutCallback
import com.winwang.moviehtml.widget.LoadingDialog

/**
 *Created by WinWang on 2020/6/8
 *Description->
 */

abstract class BaseFragment : Fragment() {

    private lateinit var mLoadService: LoadService<Any>
    private lateinit var loadingDialog: LoadingDialog
    open var mContext: FragmentActivity? = null
    open var mTopBar: QMUITopBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(getLayoutId(), container, false)
        /*******处理是否使用loadSir逻辑 */
        initTitleBar(mRootView)
        mContext = requireActivity()
        return if (useLoadSir() && !loadSirSelf()) {
            setLoadSir(mRootView)
            mLoadService.loadLayout
        } else {
            mRootView
        }
    }

    private fun initTitleBar(mRootView: View?) {
        mTopBar = mRootView?.findViewById<QMUITopBar>(R.id.qm_topbar)
        val fakeStatusBar = mRootView?.findViewById<View>(R.id.fake_status_bar)
        fakeStatusBar?.run {
            BarUtils.setStatusBarCustom(this)
        }
        mTopBar?.run {
            if (isShowBack()) {
                addLeftBackImageButton()
            }
//            BarUtils.addMarginTopEqualStatusBarHeight(this)
        }
    }

    /**
     * 是否展示返回按钮
     */
    open fun isShowBack(): Boolean {
        return false
    }

    abstract fun getLayoutId(): Int

    private fun setLoadSir(it: View) {
        mLoadService = LoadSir.getDefault().register(it) {
            mLoadService?.showCallback(LoadingCallback::class.java)
            loadNet()
        }
    }

    //自己设置loadSir布局
    protected open fun loadSirSelf(): Boolean {
        return false
    }

    //使用loadSir
    protected open fun useLoadSir(): Boolean {
        return false
    }

    open fun loadNet() {

    }

    fun showError() {
        mLoadService?.showCallback(ErrorCallback::class.java)
    }

    fun showSuccess() {
        mLoadService?.showSuccess()
    }

    fun showEmpty() {
        mLoadService?.showCallback(EmptyCallback::class.java)
    }

    fun showTimeOut() {
        mLoadService?.showCallback(TimeoutCallback::class.java)
    }

    fun showLoading() {
        mLoadService?.showCallback(LoadingCallback::class.java)
    }

    fun showToast(toastMsg: String) {
        ToastUtils.showShort(toastMsg)
    }

    fun showDialogLoading(@StringRes loadingString: Int) {
        if (this::loadingDialog.isInitialized) {
            loadingDialog = LoadingDialog.newInstace()
        }
        this.loadingDialog.show(childFragmentManager, loadingString, false)
    }

    fun hideLoading() {
        if (this::loadingDialog.isInitialized && loadingDialog.isVisible) {
            loadingDialog?.dismiss()
        }
    }


}