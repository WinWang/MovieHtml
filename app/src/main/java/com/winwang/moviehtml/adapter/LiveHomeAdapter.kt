package com.winwang.moviehtml.adapter

import android.graphics.Color
import androidx.cardview.widget.CardView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.winwang.moviehtml.R
import com.winwang.moviehtml.bean.LiveTypeBean

/**
 *Created by WinWang on 2020/6/10
 *Description->
 */
class LiveHomeAdapter(
    layoutResId: Int = R.layout.item_live_layout,
    dataList: MutableList<LiveTypeBean>
) :
    BaseQuickAdapter<LiveTypeBean, BaseViewHolder>(layoutResId, dataList) {
    override fun convert(helper: BaseViewHolder, item: LiveTypeBean) {
        item?.run {
            helper.setText(R.id.tv_live, item.liveTypeName)
            when (helper.adapterPosition) {
                0 -> helper.getView<CardView>(R.id.card_live)
                    .setCardBackgroundColor(Color.parseColor("#f58220"))
                1 -> helper.getView<CardView>(R.id.card_live)
                    .setCardBackgroundColor(Color.parseColor("#5c7a29"))
                2 -> helper.getView<CardView>(R.id.card_live)
                    .setCardBackgroundColor(Color.parseColor("#6950a1"))
                4 -> helper.getView<CardView>(R.id.card_live)
                    .setCardBackgroundColor(Color.parseColor("#008792"))
                5 -> helper.getView<CardView>(R.id.card_live)
                    .setCardBackgroundColor(Color.parseColor("#2570a1"))
                6 -> helper.getView<CardView>(R.id.card_live)
                    .setCardBackgroundColor(Color.parseColor("#5f5d46"))
                7 -> helper.getView<CardView>(R.id.card_live)
                    .setCardBackgroundColor(Color.parseColor("#ac6767"))
                else -> {

                }
            }
        }
    }
}