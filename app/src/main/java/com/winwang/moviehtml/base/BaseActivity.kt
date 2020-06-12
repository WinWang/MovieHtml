package com.winwang.moviehtml.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
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
import me.jessyan.autosize.AutoSize

/**
 *Created by WinWang on 2020/6/8
 *Description->
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayoutId(): Int
    private lateinit var mLoadService: LoadService<Any>
    private lateinit var loadingDialog: LoadingDialog
    protected var mTopBar: QMUITopBar? = null
    open var mContext: Activity? = null

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        AutoSize.autoConvertDensityOfGlobal(this)
        return super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.transparentStatusBar(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mContext = this
        initTopBar()
        initLoadSir()
        initViewData()
    }

    private fun initTopBar() {
        mTopBar = findViewById<QMUITopBar>(R.id.qm_topbar)
        val fakeStatusBar = findViewById<View>(R.id.fake_status_bar)
        fakeStatusBar?.run {
            BarUtils.setStatusBarCustom(this)
        }
        mTopBar?.run {
            if (isShowBack()) {
                addLeftBackImageButton().setOnClickListener {
                    finish()
                }
            }
        }
    }

    open fun setTitleName(title: String?) {
        title?.run {
            mTopBar?.setTitle(title)
        }
    }


    /**
     * 是否展示返回按钮
     */
    open fun isShowBack(): Boolean {
        return true
    }


    open fun initViewData() {

    }

    private fun initLoadSir() {
        if (getLayoutId() > 0) {
            val content = findViewById<LinearLayout>(R.id.view_content_loadsir)
            content?.let { setLoadSir(it) }
        }
    }

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

    fun showLoading() {
        mLoadService?.showCallback(LoadingCallback::class.java)
    }

    fun showTimeOut() {
        mLoadService?.showCallback(TimeoutCallback::class.java)
    }

    fun showToast(toastMsg: String) {
        ToastUtils.showShort(toastMsg)
    }

    fun showDialogLoading(@StringRes loadingString: Int) {
        if (this::loadingDialog.isInitialized) {
            loadingDialog = LoadingDialog.newInstace()
        }
        this.loadingDialog.show(supportFragmentManager, loadingString, false)
    }

    fun hideLoading() {
        if (this::loadingDialog.isInitialized && loadingDialog.isVisible) {
            loadingDialog?.dismiss()
        }
    }


}