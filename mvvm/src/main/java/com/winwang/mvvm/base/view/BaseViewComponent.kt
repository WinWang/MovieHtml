package com.winwang.mvvm.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.widget.FrameLayout
import androidx.lifecycle.*
import com.blankj.utilcode.util.LogUtils
import com.winwang.mvvm.base.App
import com.winwang.mvvm.base.lifecycle.MyLifecycleObserver
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.ext.showToast
import com.winwang.mvvm.widget.LoadDialog
import org.greenrobot.eventbus.EventBus
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

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
    ), MyLifecycleObserver {

    private lateinit var loadingDialog: LoadDialog

    init {
        if (getLayoutId() > -1) {
            inflate(context, getLayoutId(), this)
        }
    }


    protected lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var viewModelStoreOwner: ViewModelStoreOwner
    open var mContext: Context = context

    protected val mViewModel: VM by lazy {
        if (isDIViewModel()) {
            val clazz =
                this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<VM>
            viewModelStoreOwner!!.getViewModel<VM>(clazz = clazz)
        } else {
            val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
            ViewModelProvider(viewModelStoreOwner).get<VM>(types[0] as Class<VM>)
        }
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }


    /**
     * 是否采用依赖注入的方式注入ViewModel
     */
    open fun isDIViewModel(): Boolean = false

    open fun init() {
        lifecycleOwner = this.findViewTreeLifecycleOwner()!!
        viewModelStoreOwner = this.findViewTreeViewModelStoreOwner()!!
//        lifecycleOwner = getLifeOwner()!!
//        viewModelStoreOwner = getViewModelOwner()!!
        lifecycleOwner.lifecycle.addObserver(this)
        LogUtils.d("viewInit>>>>>>>>>>")

    }


    @Deprecated(message = "通过ViewTreeLifecycleOwner实现")
    fun getLifeOwner(): LifecycleOwner? {
        if (mContext is LifecycleOwner) {
            return mContext as LifecycleOwner
        } else if (mContext is ContextThemeWrapper) {
            return (mContext as ContextThemeWrapper).baseContext as LifecycleOwner
        } else {
            return null
        }
    }

    @Deprecated(message = "通过ViewTreeViewModelOwner实现")
    fun getViewModelOwner(): ViewModelStoreOwner? {
        if (mContext is LifecycleOwner) {
            return mContext as ViewModelStoreOwner
        } else if (mContext is ContextThemeWrapper) {
            return (mContext as ContextThemeWrapper).baseContext as ViewModelStoreOwner
        } else {
            return null
        }
    }


    override fun onDestroy(owner: LifecycleOwner) {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        owner.lifecycle.removeObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        LogUtils.d("onCreate>>>>>>>>>>")
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        initView()
        initViewModel()
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

    private fun initViewModel() {
//        mViewModel = ViewModelProvider(viewModelStoreOwner).get(viewModelClass())
    }

//    abstract fun viewModelClass(): Class<VM>

    open fun showToast(toastMessage: String) {
        App.instance.showToast(
            toastMessage
        )
    }

    fun showDialogLoading(loadingString: String? = "") {
        if (!this::loadingDialog.isInitialized) {
            context?.run {
                loadingDialog = LoadDialog(this)
            }
        }
        this.loadingDialog.showLoading(loadingString)
    }

    fun hideLoading() {
        if (this::loadingDialog.isInitialized) {
            loadingDialog.hideLoading()
        }
    }



}