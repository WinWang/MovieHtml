package com.winwang.moviehtml.bean

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.winwang.moviehtml.adapter.BannerBean

/**
 *Created by WinWang on 2020/6/4
 *Description->
 */
data class MovieBean(
    val coverUrl: String = "",
    val movieName: String = "",
    val linkUrl: String = "",
    val type: Int = 0,//Recycleview类型type
    val headTitle: String = "",  //头布局的title,
    val bannerList: MutableList<BannerBean> = mutableListOf(),
    override val itemType: Int = type //实现BRVAH多布局重写
) : MultiItemEntity {


}