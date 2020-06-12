package com.winwang.moviehtml.ui.live.detail

import androidx.lifecycle.MutableLiveData
import com.winwang.moviehtml.base.BaseViewModel
import com.winwang.moviehtml.common.Constant
import kotlinx.coroutines.Dispatchers
import org.jsoup.Jsoup

/**
 *Created by WinWang on 2020/6/12
 *Description->
 */
class TVDetailViewModel : BaseViewModel() {

    val playUrl: MutableLiveData<String> = MutableLiveData() //电影列表数据

    fun getTVDetail(path: String) {

        launch(block = {
            val deferred = async(Dispatchers.IO) {
                Jsoup.connect(Constant.BASE_LIVE_URL + path).get()
            }
            val document = deferred.await()

            document?.run {
                val elementVideo = getElementsByTag("embed")
                val attr = elementVideo[0].attr("src")
                val split = attr.split("vurl=")
                val s = split[1]
                playUrl.value = s
                println("$attr>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            }

        })
    }


}