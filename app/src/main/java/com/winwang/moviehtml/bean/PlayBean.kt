package com.winwang.moviehtml.bean

/**
 *Created by WinWang on 2020/7/15
 *Description->
 */
data class PlayBean(
    val playUrl: String = "",
    val playList: List<PlayListBean>?
) {
}