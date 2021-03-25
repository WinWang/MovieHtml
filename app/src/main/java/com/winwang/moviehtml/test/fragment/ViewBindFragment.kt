package com.winwang.moviehtml.test.fragment

import android.os.Bundle
import android.view.View
import com.winwang.moviehtml.R
import com.winwang.moviehtml.databinding.FragmentViewBindTestBinding
import com.winwang.mvvm.base.fragment.BaseVBFragment

/**
 *Created by WinWang on 2021/3/13
 *Description->测试ViewBind功能的Fragment
 */
class ViewBindFragment : BaseVBFragment<FragmentViewBindTestBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_view_bind_test

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.tvTestFragment.text = "我是Fragment里面修改文字》》》》》》"
    }

}