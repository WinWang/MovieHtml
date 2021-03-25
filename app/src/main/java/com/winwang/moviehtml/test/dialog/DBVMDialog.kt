package com.winwang.moviehtml.test.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.winwang.moviehtml.R
import com.winwang.moviehtml.databinding.LayoutDbDialogTest3Binding
import com.winwang.mvvm.base.dialog.BaseVmDBDialog
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 *Created by WinWang on 2021/3/23
 *Description->
 */
class DBVMDialog : BaseVmDBDialog<DialogViewModel, LayoutDbDialogTest3Binding>() {

    override fun getLayoutId() = R.layout.layout_db_dialog_test3

    override fun initData() {
        super.initData()
        mBinding.viewmodel = mViewModel
        lifecycleScope.launch {
            flow<Int> {
                delay(4000)
                emit(1)
            }.collect {
                mViewModel.setData()
            }
        }
    }

    override fun initObserve() {
//        mViewModel.liveData.observe(this, Observer {
//            mBinding.tvTest2.text = it
//        })
    }


}


class DialogViewModel : BaseViewModel() {

    val liveData: MutableLiveData<String> = MutableLiveData()

    fun setData() {
        liveData.value = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
    }


}
