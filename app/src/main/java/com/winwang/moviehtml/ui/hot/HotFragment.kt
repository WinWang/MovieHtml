package com.winwang.moviehtml.ui.hot

import com.winwang.mvvm.base.fragment.BaseVmFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *Created by WinWang on 2020/8/4
 *Description->
 */
class HotFragment : BaseVmFragment<HotViewModel>() {

     mViewModel :HotViewModel by viewModel()

    override fun viewModelClass(): Class<HotViewModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}