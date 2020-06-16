package com.winwang.moviehtml.ui.detail

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.winwang.moviehtml.common.Constant
import com.winwang.moviehtml.utils.SpiderUtils
import com.winwang.mvvm.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
                SpiderUtils.initJsoup("${Constant.BASE_MOVIE_URL}/$path")
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
                            delay(2000)
                            getVideoPlayUrl(vodLink)
                        }
                        LogUtils.d(">>>>>>>>>>>>$vodLink>>>>>>>$title")

                    }
                }

            }
        })
    }


    fun getVideoPlayUrl(linkUrl: String) {
        launch(
            block = {
                val job = async(Dispatchers.IO) {
                    Jsoup.connect("${Constant.BASE_MOVIE_URL}$linkUrl").get()
                }
                val document = job.await()
                delay(3000)
                val iframes = document.getElementsByTag("iframe")
                LogUtils.e(iframes.text())
                iframes?.run {
                    for ((index, element) in this.withIndex()) {
                        if (index == 1) {
                            val attr = element.attr("src")
                            playUrlEvent.value = attr
                        }
                    }
                }
            },
            error = {
                ToastUtils.showShort(it.toString())
            })
    }


}