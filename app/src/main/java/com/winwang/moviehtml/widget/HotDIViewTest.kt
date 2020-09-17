package com.winwang.moviehtml.widget

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.Observer
import com.winwang.moviehtml.ui.hot.HotViewModel
import com.winwang.mvvm.base.view.BaseDIViewComponent
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.hot_di_test_view_layout.view.*
import org.koin.core.inject

/**
 *Created by WinWang on 2020/9/17
 *Description->
 */
class HotDIViewTest @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseDIViewComponent(context, attrs) {

    private val hotViewModel: HotViewModel by inject()

    override fun initViewModel(): BaseViewModel = hotViewModel


    fun initOB() {
        lifecycleOwner?.run {
            hotViewModel.getMovieData().observe(this, Observer {
                tv_di_hot_title.text = it.toString()
                println(it.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            })
        }

    }


}