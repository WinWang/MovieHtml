package com.winwang.moviehtml.ui.live.detail

import androidx.lifecycle.MutableLiveData
import com.winwang.moviehtml.common.Constant
import com.winwang.moviehtml.ext.regexPlayUrl
import com.winwang.mvvm.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import org.jsoup.Jsoup

/**
 *Created by WinWang on 2020/6/12
 *Description->
 */
class TVDetailViewModel : BaseViewModel() {

    val playUrl: MutableLiveData<String> = MutableLiveData() //电影列表数据

    fun getTVDetail(path: String) {
        launch(
            block = {
                val deferred = async(Dispatchers.IO) {
                    Jsoup.connect("${Constant.BASE_LIVE_URL}/$path").get()
                }
                val document = deferred.await()
                document?.run {
                    val elementVideo = getElementsByTag("embed")
                    if (elementVideo.size != 0) {
                        val attr = elementVideo[0].attr("src")
                        attr?.run {
                            playUrl.value = attr.regexPlayUrl()
                        }
                    } else { //通过iframe查找
                        val frames = getElementsByTag("iframe")
                        if (frames.size > 0) {
                            for (frame in frames) {
                                val srcattr = frame.attr("src")
                                if (srcattr.contains(".htm") && srcattr.contains("app/")) {
                                    getTVDetailRetry(srcattr)
                                    return@run
                                }
                            }
                        }
                    }
                }
            }
        )
    }

    fun getTVDetailRetry(path: String) {
        println("${Constant.BASE_LIVE_URL}/$path>>>>>>>>>>>>>>>>>>>>>>>>")
        launch(
            block = {
                val deferred = async(Dispatchers.IO) {
                    Jsoup.connect("${Constant.BASE_LIVE_URL}/$path").get()
                }
                val document = deferred.await()
                document?.run {
                    val body = getElementsByTag("body")
                    val elementVideo = body[0]
                    elementVideo?.run {
                        val attr = elementVideo.attr("src")
                        attr?.run {
                            playUrl.value = attr.regexPlayUrl()
                        }
                    }


                }
            }
        )
    }


}