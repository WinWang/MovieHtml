package com.winwang.moviehtml.ui.live

import com.winwang.moviehtml.R
import com.winwang.moviehtml.adapter.TVListAdapter
import com.winwang.moviehtml.base.BaseActivity
import com.winwang.moviehtml.bean.LiveBean
import com.winwang.moviehtml.bean.LiveTypeBean
import com.winwang.moviehtml.ui.live.detail.TVDetailActivity
import com.winwang.moviehtml.utils.Router
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
        setTitleName(liveTypeBean.liveTypeName)
        val liveList = liveTypeBean.liveList
        adapter = TVListAdapter(dataList = liveList).apply {
            setOnItemClickListener { adapter, view, position ->
                val liveBean = adapter.data[position] as LiveBean
                Router.newIntent(mContext)
                    .to(TVDetailActivity::class.java)
                    .putString(TVDetailActivity.TV_NAME, liveBean.liveName)
                    .putString(TVDetailActivity.TV_LINK, liveBean.liveLink)
                    .launch()
            }
        }
        rv_tv_list.adapter = adapter
    }

}