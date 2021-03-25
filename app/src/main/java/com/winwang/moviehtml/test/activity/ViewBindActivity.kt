package com.winwang.moviehtml.test.activity

import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.FragmentUtils
import com.winwang.moviehtml.R
import com.winwang.moviehtml.databinding.ActivityViewBindTestBinding
import com.winwang.moviehtml.test.fragment.ViewBindFragment
import com.winwang.mvvm.base.activity.BaseVBActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 *Created by WinWang on 2021/3/13
 *Description->
 */
class ViewBindActivity : BaseVBActivity<ActivityViewBindTestBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_view_bind_test

    override fun initViewData() {
        super.initViewData()
        lifecycleScope.launch {
            flow {
                delay(500)
                emit(1)
            }.collect {
                showSuccess()
                mbinding.tvTest.text = "我在修改UI，做测试"
                FragmentUtils.replace(supportFragmentManager,
                    ViewBindFragment(), R.id.frame)
            }
        }

    }


}