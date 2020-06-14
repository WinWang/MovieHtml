package com.winwang.moviehtml.utils

import com.winwang.moviehtml.common.Constant
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Jsoup爬虫工具类
 */

object SpiderUtils {
    fun initJsoup(url: String = Constant.BASE_MOVIE_URL): Document {
        return Jsoup.connect(url)
            .header(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3947.100 Safari/537.36"
            )
            .header(
                "Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"
            )
            .header("Accept-Language", "zh-CN,zh;q=0.9")
            .header("Connection", "keep-alive")
            .header("Host", "www.27k.cc")
            .referrer(if (url.contains(Constant.BASE_MOVIE_URL)) Constant.BASE_MOVIE_URL else Constant.BASE_LIVE_URL)
            .get()
//            .userAgent("Mozilla/5.0 (compatible; Baiduspider/2.2; +http://www.baidu.com/search/spider.html)") //这里之所以设置这个，是为了利用百度的百度爬虫agent绕过网站的反爬虫
    }

}