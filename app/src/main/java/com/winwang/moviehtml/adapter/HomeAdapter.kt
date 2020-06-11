package com.winwang.moviehtml.adapter

import android.graphics.Color
import androidx.cardview.widget.CardView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.winwang.moviehtml.R
import com.winwang.moviehtml.bean.MovieBean
import com.winwang.moviehtml.utils.ColorRandomUtils
import com.winwang.moviehtml.utils.GlideUtils
import com.youth.banner.Banner

/**
 *Created by WinWang on 2020/6/9
 *Description->
 */
class HomeAdapter(datalist: ArrayList<MovieBean>) :
    BaseMultiItemQuickAdapter<MovieBean, BaseViewHolder>(datalist) {

    companion object {
        const val HOME_HEADER: Int = 1
        const val HOME_MOVIE: Int = 0 //普通电影
        const val HOME_BANNER: Int = 2 //banner轮播
        const val HOME_TAB: Int = 3  //小圆点
    }

    init {
        addItemType(HOME_HEADER, R.layout.item_movie_head_layout)
        addItemType(HOME_MOVIE, R.layout.item_movie_layout)
        addItemType(HOME_BANNER, R.layout.home_banner_layout)
        addItemType(HOME_TAB, R.layout.item_movie_tab_layout)
    }

    override fun convert(helper: BaseViewHolder, item: MovieBean) {
        item?.run {
            when (itemType) {
                HOME_BANNER -> {
                    val bannerView =
                        helper.getView<Banner<BannerBean, HomeBannerAdapater>>(R.id.banner_home)
                    bannerView?.run {
                        adapter = bannerList?.let { HomeBannerAdapater(it) }
                        start()
                    }
                }

                HOME_HEADER -> {
                    helper.setText(R.id.tv_head_title, item.headTitle)
                }

                HOME_MOVIE -> {
                    helper.setText(R.id.tv_movie_title, item.movieName)
                    GlideUtils.loadRadiusNetImage(
                        item.coverUrl,
                        helper.getView(R.id.iv_movie_cover)
                    )
                }

                HOME_TAB -> {
                    helper.setText(R.id.tv_movie_tab, item.movieName)
                    helper.getView<CardView>(R.id.card_movie_tab)
                        .setCardBackgroundColor(
                            Color.parseColor(
                                ColorRandomUtils.generateColor(
                                    helper.adapterPosition
                                )
                            )
                        )
                }

                else -> {

                }
            }
        }
    }


}