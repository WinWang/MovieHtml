package com.winwang.moviehtml.test.activity

import androidx.lifecycle.lifecycleScope
import com.winwang.moviehtml.R
import com.winwang.moviehtml.databinding.ActivityTestBinding
import com.winwang.mvvm.base.activity.BaseDBActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class DataBindActivity : BaseDBActivity<ActivityTestBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_test

    override fun initViewData() {
        lifecycleScope.launch {
            flow {
                delay(1000)
                emit(1)
            }.collect {
                showSuccess()
            }
        }


    }

}