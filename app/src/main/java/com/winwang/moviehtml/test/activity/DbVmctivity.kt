package com.winwang.moviehtml.test.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.winwang.moviehtml.R
import com.winwang.moviehtml.databinding.ActivityDbVmctivityBinding
import com.winwang.moviehtml.ui.hot.HomeRepository
import com.winwang.mvvm.base.activity.BaseVmDBActivity
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class DbVmctivity : BaseVmDBActivity<TestVmDbViewmodel, ActivityDbVmctivityBinding>() {


    override fun loadNet() {
        mDataBind.viewmodel = mViewModel
        lifecycleScope.launch {
            flow {
                delay(500)
                emit(1)
            }.collect {
                showSuccess()
                mViewModel.testShareViewModel()
            }
        }

    }

    override fun getLayoutId() = R.layout.activity_db_vmctivity

    override fun isDIViewModel(): Boolean = true


    override fun initObserve() {

        mViewModel.liveDataTest.observe(this, Observer {
            it?.run {
                LogUtils.d(">>>>>>>>>>>>>>>>$it")
            }
        })
    }

}


class TestVmDbViewmodel(private val homeRepository: HomeRepository) : BaseViewModel() {

    val liveDataTest: MutableLiveData<String> = MutableLiveData()

    fun testShareViewModel() {
        liveDataTest.value = "100"
    }
}
