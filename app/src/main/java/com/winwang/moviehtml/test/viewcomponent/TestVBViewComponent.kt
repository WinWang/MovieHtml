package com.winwang.moviehtml.test.viewcomponent

import android.content.Context
import android.util.AttributeSet
import com.winwang.moviehtml.databinding.ViewLayoutVbTestBinding
import com.winwang.moviehtml.ui.hot.HotViewModel
import com.winwang.mvvm.base.view.BaseVBViewComponent

/**
 *Created by WinWang on 2021/3/28
 *Description->
 */
class TestVBViewComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseVBViewComponent<HotViewModel, ViewLayoutVbTestBinding>(context, attrs) {

    override fun initData() {
        super.initData()
        mBinding.tvTestVb.text = ">>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,"
    }

}