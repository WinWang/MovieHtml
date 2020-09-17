package com.winwang.moviehtml.ui.hot

import androidx.lifecycle.Observer
import com.qmuiteam.qmui.kotlin.onClick
import com.winwang.moviehtml.R
import com.winwang.mvvm.base.fragment.BaseVmDIFragment
import kotlinx.android.synthetic.main.fragment_hot_layout.*
import kotlinx.android.synthetic.main.hot_di_test_view_layout.*
import kotlinx.android.synthetic.main.hot_test_view_layout.*
import org.greenrobot.eventbus.EventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *Created by WinWang on 2020/8/4
 *Description->
 */
class HotFragment : BaseVmDIFragment() {

    private val hotViewModel: HotViewModel by viewModel()

    override fun initViewModel(): HotViewModel = hotViewModel

    override fun getLayoutId(): Int = R.layout.fragment_hot_layout

    override fun useLoadSir() = false

    override fun initView() {
        super.initView()
        mTopBar?.setTitle(hotViewModel.test())
        hot_view.init()
        hot_di_view.init()
        button.onClick {
            sendEvent()
        }
    }

    override fun initData() {
        super.initData()

    }

    override fun lazyLoadData() {
        super.lazyLoadData()
//        hot_view.getMovieList()
        hot_di_view.initOB()
//        loadNet()
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
        super.initObserve()
        hotViewModel.getMovieData().observe(this, Observer {
            showSuccess()
            tv_test_hot.text = it.toString()
        })
    }

    fun sendEvent() {
        EventBus.getDefault().post("这是我发送的数据")
    }


}