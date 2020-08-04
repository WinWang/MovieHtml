package com.winwang.moviehtml.ui.live

import androidx.lifecycle.MutableLiveData
import com.winwang.moviehtml.bean.LiveBean
import com.winwang.moviehtml.bean.LiveTypeBean
import com.winwang.moviehtml.common.Constant
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 *Created by WinWang on 2020/6/10
 *Description->
 */
class LiveViewModel : BaseViewModel() {

    val liveTypeList: MutableLiveData<ArrayList<LiveTypeBean>> = MutableLiveData() //电影列表数据

    private var livelist: ArrayList<LiveTypeBean> = arrayListOf()

    fun getLiveList() {
        launch(block = {
            val documentJob = async(Dispatchers.IO) {
                Jsoup.connect(Constant.BASE_LIVE_URL).get()
            }
            val doucment = documentJob.await()
            setLiveListData(doucment)
        })
    }

    private fun setLiveListData(doucment: Document) {
        doucment?.run {
            val elementsByClass = getElementsByClass("pp b")
            val elements = elementsByClass[0].getElementsByClass("xyou")
            for (element in elements) {
                val title = element.getElementsByClass("cy").text()
                val elementsByTag = element.getElementsByTag("a")
                var tempList = arrayListOf<LiveBean>()
                for (element in elementsByTag) {
                    val elementsByAttribute = element.getElementsByAttribute("href")
                    val itemText = elementsByAttribute.text()
                    val linkUrl = elementsByAttribute.attr("href")
                    tempList.add(LiveBean(itemText, linkUrl))
                }
                livelist.add(LiveTypeBean(title, tempList))
            }
            liveTypeList.value = livelist
        }
    }

}