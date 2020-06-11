package com.winwang.moviehtml.ui.live

import com.winwang.moviehtml.R
import com.winwang.moviehtml.adapter.TVListAdapter
import com.winwang.moviehtml.base.BaseActivity
import com.winwang.moviehtml.bean.LiveTypeBean
import kotlinx.android.synthetic.main.activity_tv_list_layout.*

/**
 *Created by WinWang on 2020/6/10
 *Description->
 */
class LiveListActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_tv_list_layout

    lateinit var adapter: TVListAdapter

    override fun initViewData() {
        super.initViewData()
        val liveTypeBean = intent.getSerializableExtra("tvList") as LiveTypeBean
        mTopBar?.setTitle(liveTypeBean.liveTypeName)
        val liveList = liveTypeBean.liveList
        adapter = TVListAdapter(dataList = liveList).apply {
            setOnItemClickListener { adapter, view, position ->

            }
        }
        rv_tv_list.adapter = adapter
    }
}