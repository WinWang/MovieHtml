package com.winwang.moviehtml.ui.home

import com.winwang.moviehtml.R
import com.winwang.mvvm.base.dialog.BaseVmDialog
import kotlinx.android.synthetic.main.hot_test_view_layout.*

/**
 *Created by WinWang on 2020/6/16
 *Description->测试Dialog的职能
 */
class TestDialog : BaseVmDialog<HomeViewModel>() {

    override fun getLayoutId() = R.layout.dialog_test_layout

    override fun useLoadSir(): Boolean = true

    override fun initData() {
        loadNet()
        hot_view.init()
        hot_view.getMovieList()
    }

    override fun loadNet() {
        super.loadNet()
        mViewModel.getMovieList()

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}