package com.winwang.moviehtml.ui.detail

import androidx.lifecycle.Observer
import cn.jzvd.Jzvd
import com.winwang.moviehtml.R
import com.winwang.moviehtml.utils.GlideUtils
import com.winwang.mvvm.base.activity.BaseVmActivity
import kotlinx.android.synthetic.main.activity_video_detail_layout.*

/**
 *Created by WinWang on 2020/6/12
 *Description->
 */
class VideoDetailActivity : BaseVmActivity<VideoDetailViewModel>() {

    companion object {
        const val VIDEO_DETAIL_KEY = "video_detail_key"
        const val VIDEO_NAME = "video_name"
        const val VIDEO_COVER = "video_cover"
    }

    var videoUrl: String = ""

    override fun viewModelClass(): Class<VideoDetailViewModel> = VideoDetailViewModel::class.java

    override fun getLayoutId(): Int = R.layout.activity_video_detail_layout

    override fun initData() {
        setTitleName(intent.getStringExtra(VIDEO_NAME))
        videoUrl = intent.getStringExtra(VIDEO_DETAIL_KEY)
        GlideUtils.loadRadiusNetImage(intent.getStringExtra(VIDEO_COVER), iv_cover_movie)
    }

    override fun loadNet() {
        mViewModel.getVideoDetail(videoUrl)
    }


    override fun initObserve() {
        super.initObserve()
        mViewModel.playUrlEvent.observe(this, Observer {
            video_player.setUp(it, "")
            video_player.startButton.performClick()
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