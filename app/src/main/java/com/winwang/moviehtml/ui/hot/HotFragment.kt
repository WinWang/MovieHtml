package com.winwang.moviehtml.ui.hot

import com.winwang.moviehtml.R
import com.winwang.mvvm.base.fragment.BaseVmDIFragment
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.fragment_hot_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *Created by WinWang on 2020/8/4
 *Description->
 */
class HotFragment : BaseVmDIFragment() {

    private val hotViewModel: HotViewModel by viewModel()

    override fun initViewModel(): BaseViewModel = hotViewModel

    override fun getLayoutId(): Int = R.layout.fragment_hot_layout

    override fun initView() {
        super.initView()
        mTopBar?.setTitle(hotViewModel.test())
    }

}