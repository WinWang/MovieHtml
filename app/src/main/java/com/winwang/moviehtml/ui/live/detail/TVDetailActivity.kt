package com.winwang.moviehtml.ui.live.detail

import androidx.lifecycle.Observer
import com.winwang.moviehtml.R
import com.winwang.moviehtml.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_tv_detail_layout.*

/**
 *Created by WinWang on 2020/6/12
 *Description->
 */
class TVDetailActivity : BaseVmActivity<TVDetailViewModel>() {

    companion object {
        const val TV_LINK = "tv_link"
        const val TV_NAME = "tv_name"
    }

    var tvUrl: String = ""

    override fun viewModelClass(): Class<TVDetailViewModel> = TVDetailViewModel::class.java

    override fun getLayoutId(): Int = R.layout.activity_tv_detail_layout

    override fun initData() {
        tvUrl = intent.getStringExtra(TV_LINK)
        setTitleName(intent.getStringExtra(TV_NAME))
    }

    override fun loadNet() {
        mViewModel.getTVDetail(tvUrl)
    }

    override fun initObserve() {
        super.initObserve()
        mViewModel.playUrl.observe(this, Observer {
            tv_player.setUp("http://cctvalih5ca.v.myalicdn.com/live/cctv1_2/index.m3u8", "")
        })
    }

}