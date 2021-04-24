package com.winwang.moviehtml.test.activity

import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.FragmentUtils
import com.winwang.moviehtml.R
import com.winwang.moviehtml.databinding.ActivityViewBindTestBinding
import com.winwang.moviehtml.test.dialog.VMVBDialog
import com.winwang.moviehtml.test.fragment.ViewBindVMFragment
import com.winwang.mvvm.base.activity.BaseVmVBActivity
import kotlinx.android.synthetic.main.activity_view_bind_test.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 *Created by WinWang on 2021/3/13
 *Description->测试viewmodel+Viewbind功能的Activity
 */
class ViewBindVMActivity : BaseVmVBActivity<TestVmDbViewmodel, ActivityViewBindTestBinding>() {

    override fun isDIViewModel() = true

    override fun initObserve() {
        mViewModel.liveDataTest.observe(this, Observer {
            mbinding.tvTest.text = it
        })
    }

    override fun getLayoutId() = R.layout.activity_view_bind_test


    override fun initView() {
        super.initView()
        tv_test_1.init()
    }

    override fun initData() {
        super.initData()
        lifecycleScope.launch {
            flow {
                delay(500)
                emit(1)
            }.collect {
                showSuccess()
                mViewModel.testShareViewModel()

                FragmentUtils.replace(
                    supportFragmentManager,
                    ViewBindVMFragment(), R.id.frame
                )
            }
        }

//        ViewBindingDialog().show(supportFragmentManager, "")

//        DataBindingDialog().show(supportFragmentManager,"")

//        DBVMDialog().show(supportFragmentManager, "")

        VMVBDialog().show(supportFragmentManager, "")


    }


}