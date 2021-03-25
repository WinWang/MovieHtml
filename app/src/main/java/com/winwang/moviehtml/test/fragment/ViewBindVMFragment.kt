package com.winwang.moviehtml.test.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.winwang.moviehtml.R
import com.winwang.moviehtml.databinding.FragmentViewBindTestBinding
import com.winwang.mvvm.base.fragment.BaseVmVBFragment
import com.winwang.mvvm.base.viewmodel.BaseViewModel

/**
 *Created by WinWang on 2021/3/15
 *Description->测试viewmodel+Viewbind功能的Fragment
 */
class ViewBindVMFragment : BaseVmVBFragment<ViewBindVMViewModel, FragmentViewBindTestBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_view_bind_test


    override fun initData() {
        super.initData()
        mViewModel.testShareViewModel()
    }

    override fun initObserve() {
        mViewModel.liveDataTest.observe(this, Observer {
            mBinding.tvTestFragment.text = it
        })
    }
}


class ViewBindVMViewModel : BaseViewModel() {
    val liveDataTest: MutableLiveData<String> = MutableLiveData()

    fun testShareViewModel() {
        liveDataTest.value = "Fragment测试数据修改"
    }
}
