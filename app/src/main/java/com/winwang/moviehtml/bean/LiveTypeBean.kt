package com.winwang.moviehtml.bean

import java.io.Serializable

/**
 *Created by WinWang on 2020/6/10
 *Description->直播类别的数据
 */
data class LiveTypeBean(
    val liveTypeName: String,
    val liveList: MutableList<LiveBean>
) : Serializable