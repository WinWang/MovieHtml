package com.winwang.mvvm.base.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.winwang.mvvm.R
import com.winwang.mvvm.loadsir.EmptyCallback
import com.winwang.mvvm.loadsir.ErrorCallback
import com.winwang.mvvm.loadsir.LoadingCallback
import com.winwang.mvvm.loadsir.TimeoutCallback

/**
 *Created by WinWang on 2020/6/16
 *Description->通用的Dialog
 */
abstract class BaseDialog : DialogFragment() {

    var mLoadService: LoadService<Any>? = null
    open var mContext: FragmentActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(getLayoutId(), container, false)
        mContext = requireActivity()
        if (useLoadSir()) {
            val content = mRootView.findViewById<View>(R.id.view_content_loadsir)
            content?.let { setLoadSir(it) }
        }
        return mRootView
    }

    override fun onStart() {
        super.onStart()
        val window = dialog!!.window
        val params = window!!.attributes
        params.gravity = setGravity()
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = params
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //注意此处
    }

    protected open fun setGravity(): Int {
        return Gravity.CENTER
    }

    //使用loadSir
    protected open fun useLoadSir(): Boolean {
        return false
    }

    abstract fun getLayoutId(): Int

    private fun setLoadSir(it: View) {
        mLoadService = LoadSir.getDefault().register(it) {
            mLoadService?.showCallback(LoadingCallback::class.java)
            loadNet()
        }
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
//        ToastUtils.showShort(toastMsg)
    }


}