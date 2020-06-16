package com.winwang.moviehtml.ui.detail

import androidx.lifecycle.Observer
import com.winwang.moviehtml.R
import com.winwang.mvvm.base.BaseVmActivity
import com.youth.banner.util.LogUtils
import kotlinx.android.synthetic.main.activity_video_detail_layout.*

/**
 *Created by WinWang on 2020/6/12
 *Description->
 */
class VideoDetailActivity : BaseVmActivity<VideoDetailViewModel>() {

    companion object {
        const val VIDEO_DETAIL_KEY = "video_detail_key"
        const val VIDEO_NAME = "video_name"
    }

    var videoUrl: String = ""

    override fun viewModelClass(): Class<VideoDetailViewModel> = VideoDetailViewModel::class.java

    override fun getLayoutId(): Int = R.layout.activity_video_detail_layout

    override fun initData() {
        setTitleName(intent.getStringExtra(VIDEO_NAME))
        videoUrl = intent.getStringExtra(VIDEO_DETAIL_KEY)
    }

    override fun loadNet() {
        mViewModel.getVideoDetail(videoUrl)
    }


    override fun initObserve() {
        super.initObserve()
        mViewModel.playUrlEvent.observe(this, Observer {
            LogUtils.d(it)
            video_player.setUp(it, "")
        })


    }


}