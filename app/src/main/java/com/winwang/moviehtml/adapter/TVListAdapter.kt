package com.winwang.moviehtml.adapter

import android.graphics.Color
import androidx.cardview.widget.CardView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.winwang.moviehtml.R
import com.winwang.moviehtml.bean.LiveBean
import com.winwang.moviehtml.utils.ColorRandomUtils

/**
 *Created by WinWang on 2020/6/10
 *Description->
 */
class TVListAdapter(itemId: Int = R.layout.item_tv_layout, dataList: MutableList<LiveBean>) :
    BaseQuickAdapter<LiveBean, BaseViewHolder>(itemId, dataList) {
    override fun convert(holder: BaseViewHolder, item: LiveBean) {
        item?.run {
            holder.setText(R.id.tv_title, item.liveName)
            holder.getView<CardView>(R.id.card_tv)
                .setCardBackgroundColor(Color.parseColor(ColorRandomUtils.generateColor(holder.adapterPosition)))
        }
    }


}