package com.winwang.moviehtml.ui.live.detail

import androidx.lifecycle.Observer
import cn.jzvd.Jzvd
import com.winwang.moviehtml.R
import com.winwang.mvvm.base.activity.BaseVmActivity
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
    var tvName: String = ""

    override fun getLayoutId(): Int = R.layout.activity_tv_detail_layout

    override fun initData() {
        tvUrl = intent.getStringExtra(TV_LINK)
        tvName = intent.getStringExtra(TV_NAME)
        setTitleName(tvName)
    }

    override fun loadNet() {
        mViewModel.getTVDetail(tvUrl)
    }

    override fun initObserve() {
        super.initObserve()
        mViewModel.playUrl.observe(this, Observer {
            //            tv_player.setUp(it, tvName)
            tv_player.setUp(it, tvName)
            tv_player.startButton.performClick()

        })
    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()
    }


}