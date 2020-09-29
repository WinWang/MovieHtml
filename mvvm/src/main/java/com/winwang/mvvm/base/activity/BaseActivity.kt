package com.winwang.mvvm.base.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.savedstate.ViewTreeSavedStateRegistryOwner
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.qmuiteam.qmui.widget.QMUITopBar
import com.winwang.mvvm.R
import com.winwang.mvvm.base.IView
import com.winwang.mvvm.base.view.BaseViewComponent
import com.winwang.mvvm.loadsir.EmptyCallback
import com.winwang.mvvm.loadsir.ErrorCallback
import com.winwang.mvvm.loadsir.LoadingCallback
import com.winwang.mvvm.loadsir.TimeoutCallback
import com.winwang.mvvm.widget.LoadingDialog
import me.jessyan.autosize.AutoSize
import org.greenrobot.eventbus.EventBus

/**
 *Created by WinWang on 2020/6/8
 *Description->
 */
abstract class BaseActivity : AppCompatActivity(), IView {

    abstract fun getLayoutId(): Int
    private var mLoadService: LoadService<Any>? = null
    private lateinit var loadingDialog: LoadingDialog
    open var mTopBar: QMUITopBar? = null
    open var mContext: Activity? = null

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        AutoSize.autoConvertDensityOfGlobal(this)
        return super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.transparentStatusBar(this)
        super.onCreate(savedInstanceState)
        initViewTreeOwners()
        setContentView(getLayoutId())
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        mContext = this
        initTopBar()
        initLoadSir()
        initViewData()
    }

    /**
     * 为了给自定义的ViewComponent内部使用，能正常使用lifecycleOwner和viewModelScope----liveData+ViewModel的模式开发
     */
    fun initViewTreeOwners() {
        // Set the view tree owners before setting the content view so that the inflation process
        // and attach listeners will see them already present
        ViewTreeLifecycleOwner.set(window.decorView, this)
        ViewTreeViewModelStoreOwner.set(window.decorView, this)
        ViewTreeSavedStateRegistryOwner.set(window.decorView, this)
    }


    private fun initTopBar() {
        mTopBar = findViewById(R.id.qm_topbar)
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
    open fun isShowBack(): Boolean = true

    protected open fun useEventBus(): Boolean = false


    open fun initViewData() {

    }

    private fun initLoadSir() {
        if (getLayoutId() > 0) {
            val content = findViewById<View>(R.id.view_content_loadsir)
            content?.let {
                setLoadSir(it)
            }
        }
    }

    private fun setLoadSir(it: View) {
        mLoadService = LoadSir.getDefault().register(it) {
            mLoadService?.showCallback(LoadingCallback::class.java)
            loadNet()
        }
    }

    fun initViewComponent(it: View) {
        it?.run {
            if (this is ViewGroup) {
                var vp: ViewGroup = it as ViewGroup
                (0..vp.childCount).forEachIndexed { index, item ->
                    val childAt = this.getChildAt(index)
                    if (childAt is ViewGroup) {
                        if (childAt is BaseViewComponent<*>) {
                            val baseViewComponent = childAt as BaseViewComponent<*>
                            baseViewComponent.init()
                        } else {
                            initViewComponent(childAt)
                        }
                    }
                }
            }
        }
    }

    open fun loadNet() {

    }

    fun showError() {
        hideRefresh()
        mLoadService?.showCallback(ErrorCallback::class.java)
    }

    fun showSuccess() {
        hideRefresh()
        mLoadService?.showSuccess()
    }

    fun showEmpty() {
        hideRefresh()
        mLoadService?.showCallback(EmptyCallback::class.java)
    }

    fun showLoading() {
        hideRefresh()
        mLoadService?.showCallback(LoadingCallback::class.java)
    }

    fun showTimeOut() {
        hideRefresh()
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

    override fun onDestroy() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        super.onDestroy()
    }

    override fun hideRefresh() {
        super.hideRefresh()
    }


}