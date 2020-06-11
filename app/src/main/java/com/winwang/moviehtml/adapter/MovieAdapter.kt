package com.winwang.moviehtml.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.winwang.moviehtml.R
import com.winwang.moviehtml.bean.MovieBean
import com.winwang.moviehtml.utils.GlideUtils

/**
 *Created by WinWang on 2020/6/4
 *Description->
 */
class MovieAdapter(layoutResId: Int = R.layout.item_movie_layout) :
    BaseQuickAdapter<MovieBean, BaseViewHolder>(layoutResId) {

    override fun convert(holder: BaseViewHolder, item: MovieBean) {
        GlideUtils.loadNetImage(item.coverUrl, holder.getView(R.id.iv_movie_cover))
    }
}