package com.winwang.moviehtml.ui.hot

import androidx.lifecycle.Observer
import com.qmuiteam.qmui.kotlin.onClick
import com.winwang.moviehtml.R
import com.winwang.mvvm.base.fragment.BaseVmDIFragment
import com.winwang.mvvm.base.fragment.BaseVmFragment
import com.youth.banner.util.LogUtils
import kotlinx.android.synthetic.main.fragment_hot_layout.*
import kotlinx.android.synthetic.main.hot_di_test_view_layout.*
import org.greenrobot.eventbus.EventBus

/**
 *Created by WinWang on 2020/8/4
 *Description->
 */
class HotFragment : BaseVmFragment<HotViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_hot_layout

    override fun useLoadSir() = false

    override fun initView() {
        super.initView()
        mTopBar?.setTitle(mViewModel.test())
//        hot_view.init()
//        hot_di_view.init()
        button.onClick {
            sendEvent()
        }
    }

    override fun isDIViewModel() = true


    override fun initData() {
        super.initData()

    }

    override fun lazyLoadData() {
        super.lazyLoadData()
//        hot_view.getMovieList()
//        hot_di_view.initObserve()
        loadNet()
        LogUtils.e(">>>>>>>>>>>>Hot>>>>${mViewModel.toString()}")
    }

    override fun loadNet() {
        super.loadNet()
//        hot_view.getMovieList()
//        hotViewModel.getTestData()
//        showSuccess()
//        hotViewModel.getTestData()
//        initObserve()

    }

    override fun initObserve() {
        mViewModel.getMovieData().observe(this, Observer {
            showSuccess()
            tv_di_hot_title.text = it.toString()
        })

        mViewModel.liveDataTest.observe(this, Observer {
            tv_hot_txt.text = it
        })

    }

    fun sendEvent() {
        EventBus.getDefault().post("这是我发送的数据")
    }


}