package com.winwang.moviehtml.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.winwang.moviehtml.R
import com.winwang.moviehtml.utils.GlideUtils
import com.youth.banner.adapter.BannerAdapter

/**
 *Created by WinWang on 2020/6/11
 *Description->
 */
class HomeBannerAdapater(dataList: MutableList<BannerBean>) :
    BannerAdapter<BannerBean, BaseViewHolder>(dataList) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        //注意布局文件，item布局文件要设置为match_parent，这个是viewpager2强制要求的
//或者调用BannerUtils.getView(parent,R.layout.banner_image_title_num);
        val view: View = LayoutInflater.from(parent!!.context)
            .inflate(R.layout.item_banner_layout, parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindView(holder: BaseViewHolder?, data: BannerBean?, position: Int, size: Int) {
        data?.run {
            GlideUtils.loadGasNetImage(
                data?.coverUrl,
                holder!!.getView<ImageView>(R.id.back_banner)
            )
            GlideUtils.loadNetImage(data?.coverUrl, holder!!.getView(R.id.iv_banner_cover))
        }

    }
}