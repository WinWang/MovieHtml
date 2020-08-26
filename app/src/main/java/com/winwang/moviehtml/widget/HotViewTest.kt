package com.winwang.moviehtml.widget

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.Observer
import com.winwang.moviehtml.ui.home.HomeViewModel
import com.winwang.mvvm.base.view.BaseViewComponent
import kotlinx.android.synthetic.main.hot_test_view_layout.view.*

/**
 *Created by WinWang on 2020/8/26
 *Description->
 */
class HotViewTest @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseViewComponent<HomeViewModel>(context, attrs, defStyleAttr) {

    override fun viewModelClass() = HomeViewModel::class.java

    override fun initView() {
        super.initView()
        tv_hot_title?.text = "这是我修改的标题"
    }

    override fun initData() {
        super.initData()
//        mViewModel.getMovieList()
    }

    override fun initObserve() {
        super.initObserve()
//        lifecycleOwner?.run {
//            mViewModel.liveMovieList.observe(this, Observer {
//                tv_hot_title.text = it.toString()
//            })
//        }

    }


}