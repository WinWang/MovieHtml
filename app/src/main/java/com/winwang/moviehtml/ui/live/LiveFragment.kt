package com.winwang.moviehtml.ui.live

import androidx.lifecycle.Observer
import com.winwang.moviehtml.R
import com.winwang.moviehtml.adapter.LiveHomeAdapter
import com.winwang.moviehtml.bean.LiveTypeBean
import com.winwang.moviehtml.ui.home.TestDialog
import com.winwang.moviehtml.utils.Router
import com.winwang.mvvm.base.fragment.BaseVmFragment
import kotlinx.android.synthetic.main.fragment_live_layout.*

/**
 *Created by WinWang on 2020/6/10
 *Description->
 */
class LiveFragment : BaseVmFragment<LiveViewModel>() {

    val datalist = arrayListOf<LiveTypeBean>()

    lateinit var adapter: LiveHomeAdapter

    override fun getLayoutId(): Int = R.layout.fragment_live_layout

    override fun useLoadSir(): Boolean = true

    override fun initData() {
        super.initData()
        mTopBar?.setTitle("直播")
        adapter = LiveHomeAdapter(dataList = datalist).apply {
            setOnItemClickListener { _adapter, view, position ->
                val itemData = adapter.data[position]
                Router.newIntent(mContext)
                    .to(LiveListActivity::class.java)
                    .putSerializable("tvList", itemData)
                    .launch()
            }
        }
        rv_live.adapter = adapter
    }


    override fun loadNet() {
        showDialogLoading(null)
        TestDialog().show(childFragmentManager,"home1")
        mViewModel.getLiveList()
    }

    override fun lazyLoadData() {
        loadNet()
    }

    override fun initObserve() {
        super.initObserve()
        mViewModel.run {
            liveTypeList.observe(viewLifecycleOwner, Observer {
                adapter.setNewInstance(it)
                hideLoading()
            })
        }
    }


}