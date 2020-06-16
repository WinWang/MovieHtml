package com.winwang.moviehtml.ui.home

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.winwang.moviehtml.adapter.BannerBean
import com.winwang.moviehtml.adapter.HomeAdapter
import com.winwang.moviehtml.base.BaseViewModel
import com.winwang.moviehtml.bean.MovieBean
import com.winwang.moviehtml.utils.SpiderUtils
import kotlinx.coroutines.Dispatchers
import org.jsoup.nodes.Document

/**
 *Created by WinWang on 2020/6/8
 *Description->
 */
class HomeViewModel : BaseViewModel() {

    val liveMovieList: MutableLiveData<ArrayList<MovieBean>> = MutableLiveData() //电影列表数据

    private var movieList: ArrayList<MovieBean> = arrayListOf()
    private var bannerList: ArrayList<BannerBean> = arrayListOf()

    fun getMovieList() {
        launch(
            block = {
                val documentJob = async(Dispatchers.IO) {
                    SpiderUtils.initJsoup()
                }
                val document = documentJob.await()
                document?.run {
                    setHotMovie(this)
                    setTabData(this)
                    movieList.add(MovieBean(type = HomeAdapter.HOME_HEADER, headTitle = "最新电影"))
                    setNewMovie(this)
                    movieList.add(MovieBean(type = HomeAdapter.HOME_HEADER, headTitle = "最新电视剧"))
                    setTV(this)
                    movieList.add(MovieBean(type = HomeAdapter.HOME_HEADER, headTitle = "动漫综艺"))
                    setCartoon(this)
                    movieList.add(MovieBean(type = HomeAdapter.HOME_HEADER, headTitle = "娱乐综艺"))
                    setEntertainment(this)
                    liveMovieList.value = movieList
                }
            },
            error = {
                LogUtils.e(it.toString())
            }
        )
    }

    private fun setEntertainment(document: Document) {
        //获取娱乐
        val entertainmentAndCartoonElement =
            document.getElementsByClass("col-sm-12 col-md-12 col-lg-9 col-xlg-9 section-list sl8")
        val cartoonEle = entertainmentAndCartoonElement[1].children()
        for (element in cartoonEle) {
            val coverElement = element.getElementsByClass("img-responsive")
            val imgElement = coverElement[0].getElementsByTag("img")
            val imgCover = imgElement[0].attr("src")
            val titleH4 = element.getElementsByTag("h4")
            val linkUrl = titleH4[0].getElementsByTag("a").attr("href")
            movieList.add(MovieBean(imgCover, titleH4.text(), linkUrl))
        }

    }

    private fun setCartoon(document: Document) {
        // 动漫和综艺节目
        val entertainmentAndCartoonElement =
            document.getElementsByClass("col-sm-12 col-md-12 col-lg-9 col-xlg-9 section-list sl8")
        //获取动漫
        val entertainmentEle = entertainmentAndCartoonElement[0].children()
        for (element in entertainmentEle) {
            val coverElement = element.getElementsByClass("img-responsive")
            val imgElement = coverElement[0].getElementsByTag("img")
            val imgCover = imgElement[0].attr("src")
            val titleH4 = element.getElementsByTag("h4")
            val linkUrl = titleH4[0].getElementsByTag("a").attr("href")
            movieList.add(MovieBean(imgCover, titleH4.text(), linkUrl))
        }
    }

    private fun setTV(document: Document) {
        //最新电视剧
        val hotTVElements =
            document.getElementsByClass("col-sm-12 col-md-12 col-lg-9 col-xlg-9 section-list sl4")
        val childrenTVElement = hotTVElements[0].children()
        for (element in childrenTVElement) {
            val coverElement = element.getElementsByClass("img-responsive")
            val imgElement = coverElement[0].getElementsByTag("img")
            val imgCover = imgElement[0].attr("src")
            val titleH4 = element.getElementsByTag("h4")
            val linkUrl = titleH4[0].getElementsByTag("a").attr("href")
            movieList.add(MovieBean(imgCover, titleH4.text(), linkUrl))
        }
    }

    private fun setHotMovie(document: Document) {
        //热门标签
        val hotBanner = document.getElementById("hot_1")
        val hotBanners = hotBanner.getElementsByClass("list_mov")
        for (banner in hotBanners) {
            val coverElement = banner.getElementsByClass("list_mov_poster")
            val imgElement = coverElement[0].getElementsByTag("img")
            val imgCover = imgElement[0].attr("src")
            val titleH4 = banner.getElementsByTag("h4")
            val bannerTitle = banner.getElementsByTag("a")
            val linkUrl = bannerTitle.attr("href")
            bannerList.add(BannerBean(imgCover, titleH4.text(), linkUrl))
        }
        movieList.add(MovieBean(type = HomeAdapter.HOME_BANNER, bannerList = bannerList))
    }

    /**
     * 热门电影
     */
    private fun setNewMovie(document: Document?) {
        //最新电影
        val hotMovieElements =
            document?.getElementsByClass("col-sm-12 col-md-12 col-lg-9 col-xlg-9 section-list sl2")
        val childrenElement = hotMovieElements?.get(0)?.children()
        if (childrenElement != null) {
            for (element in childrenElement) {
                val coverElement = element.getElementsByClass("img-responsive")
                val imgElement = coverElement[0].getElementsByTag("img")
                val imgCover = imgElement[0].attr("src")
                val elementsByTag = element.getElementsByTag("em")
                val titleH4 = element.getElementsByTag("h4")
                val linkUrl = titleH4[0].getElementsByTag("a").attr("href")
                movieList.add(MovieBean(imgCover, titleH4.text(), linkUrl))
            }
        }
    }


    //查找tab栏
    private fun setTabData(document: Document) {
        val elementsByClass = document.getElementsByClass("nav navbar-nav")
        val children = elementsByClass[0].children()
        for (child in children) {
            val tabTag = child.getElementsByTag("a")
            val tabTitle = tabTag.text() //标题
            val href = tabTag[0].attr("href") //跳转连接
            movieList.add(
                MovieBean(
                    type = HomeAdapter.HOME_TAB,
                    movieName = tabTitle,
                    linkUrl = href
                )
            )

        }
    }


}