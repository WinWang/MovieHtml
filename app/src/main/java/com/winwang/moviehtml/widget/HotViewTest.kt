package com.winwang.moviehtml.widget

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.winwang.moviehtml.widget.viewmodel.HotViewViewModel
import com.winwang.mvvm.base.view.BaseViewComponent
import kotlinx.android.synthetic.main.hot_test_view_layout.view.*

/**
 *Created by WinWang on 2020/8/26
 *Description->
 */
class HotViewTest @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseViewComponent<HotViewViewModel>(context, attrs) {

    override fun viewModelClass() = HotViewViewModel::class.java


    override fun initView() {
        super.initView()
        tv_hot_title?.text = "这是我修改的标题"
    }

    override fun initData() {
        super.initData()
        mViewModel.getMovieList()

    }

    override fun initObserve() {
        super.initObserve()
        lifecycleOwner?.run {
            mViewModel.movieListLiveData.observe(this, Observer {
                tv_hot_title.text = it.toString()
                println(it.toString()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            })
        }

    }


}