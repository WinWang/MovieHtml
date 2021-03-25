package com.winwang.moviehtml.test.dialog

import android.os.Bundle
import android.view.View
import com.winwang.moviehtml.R
import com.winwang.moviehtml.databinding.LayoutDbDialogTestBinding
import com.winwang.mvvm.base.dialog.BaseDBDialog

/**
 *Created by WinWang on 2021/3/23
 *Description->
 */
class DataBindingDialog : BaseDBDialog<LayoutDbDialogTestBinding>() {

    override fun getLayoutId() = R.layout.layout_db_dialog_test

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.tvTest.text = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
    }


}