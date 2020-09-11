package com.winwang.moviehtml.widget

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.Observer
import com.winwang.moviehtml.widget.viewmodel.HotViewViewModel
import com.winwang.mvvm.base.view.BaseViewComponent
import kotlinx.android.synthetic.main.hot_test_view_layout.view.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *Created by WinWang on 2020/8/26
 *Description->
 */
class HotViewTest @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseViewComponent<HotViewViewModel>(context, attrs) {

    override fun viewModelClass() = HotViewViewModel::class.java

    override fun useEventBus(): Boolean = true

    override fun initView() {
        super.initView()
        tv_hot_title?.text = "这是我修改的标题"
    }

    open fun getMovieList() {
        mViewModel.getMovieList()
    }

    override fun initObserve() {
        super.initObserve()
        lifecycleOwner?.run {
            mViewModel.movieListLiveData.observe(this, Observer {
                tv_hot_title.text = it.toString()
                println(it.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            })
        }

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun response(message: String) {
        showToast(message)
    }


}