package com.winwang.moviehtml.test.dialog

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.winwang.moviehtml.R
import com.winwang.moviehtml.databinding.LayoutDialogTestBinding
import com.winwang.mvvm.base.dialog.BaseVBDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 *Created by WinWang on 2021/3/23
 *Description->
 */
class ViewBindingDialog : BaseVBDialog<LayoutDialogTestBinding>() {

    override fun getLayoutId(): Int = R.layout.layout_dialog_test

    override fun useLoadSir() = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            flow {
                delay(5000)
                emit(1)
            }.collect {
                showSuccess()
                showToast("哈哈哈哈")
            }


        }
    }

}