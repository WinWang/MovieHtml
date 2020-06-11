package com.winwang.moviehtml.bean

import java.io.Serializable

/**
 *Created by WinWang on 2020/6/10
 *Description->直播类内部数据
 */
data class LiveBean(
    val liveName: String,
    var liveLink: String
) : Serializable