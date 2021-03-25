package com.winwang.moviehtml.test.dialog

import androidx.lifecycle.Observer
import com.winwang.moviehtml.R
import com.winwang.moviehtml.databinding.LayoutDbDialogTest5Binding
import com.winwang.mvvm.base.dialog.BaseVmVBDialog

/**
 *Created by WinWang on 2021/3/23
 *Description->
 */
class VMVBDialog : BaseVmVBDialog<DialogViewModel, LayoutDbDialogTest5Binding>() {

    override fun getLayoutId(): Int = R.layout.layout_db_dialog_test_5

    override fun initData() {
        super.initData()
        loadNet()
    }

    override fun loadNet() {
        super.loadNet()
        mViewModel.setData()
    }

    override fun initObserve() {
        mViewModel.liveData.observe(this, Observer {
            mBinding.tvDialog.text = it
        })
    }


}





