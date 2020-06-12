package com.winwang.moviehtml.ui.detail

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.winwang.moviehtml.base.BaseViewModel
import com.winwang.moviehtml.bean.MovieBean
import com.winwang.moviehtml.common.Constant
import kotlinx.coroutines.Dispatchers
import org.jsoup.Jsoup

/**
 *Created by WinWang on 2020/6/12
 *Description->
 */

class VideoDetailViewModel : BaseViewModel() {


    val playUrlEvent: MutableLiveData<String> = MutableLiveData() //电影列表数据


    fun getVideoDetail(path: String) {
        launch(block = {
            val job = async(Dispatchers.IO) {
                Jsoup.connect("${Constant.BASE_MOVIE_URL}/$path").get()
            }
            val document = job.await()
            document?.run {
                val linkDiv = getElementById("vlink_1")//获取播放连接的div包裹
                val linkATag = linkDiv.getElementsByTag("a")
                linkATag?.run {
                    for ((index, element) in linkATag.withIndex()) {
                        val vodLink = element.attr("href")
                        val title = element.text()
                        if (index == 0) {
                            getVideoPlayUrl(vodLink)
                        }
                        LogUtils.d(">>>>>>>>>>>>$vodLink>>>>>>>$title")

                    }
                }

            }
        })
    }


    fun getVideoPlayUrl(linkUrl: String) {
        launch(block = {
            val job = async(Dispatchers.IO) {
                Jsoup.connect("${Constant.BASE_MOVIE_URL}$linkUrl").get()
            }
            val document = job.await()
//            LogUtils.d(document.toString())
            val iframes = document.getElementsByTag("iframe")
            iframes?.run {
                for ((index, element) in this.withIndex()) {
                    if (index == 1) {
                        val attr = element.attr("src")
                        playUrlEvent.value = attr
                    }
                }
            }

        })
    }


}