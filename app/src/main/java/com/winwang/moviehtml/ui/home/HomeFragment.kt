package com.winwang.moviehtml.ui.home

import androidx.lifecycle.Observer
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration
import com.winwang.moviehtml.R
import com.winwang.moviehtml.adapter.HomeAdapter
import com.winwang.moviehtml.base.BaseVmFragment
import com.winwang.moviehtml.bean.MovieBean
import kotlinx.android.synthetic.main.fragment_home.*


/**
 *Created by WinWang on 2020/6/8
 *Description->
 */
class HomeFragment : BaseVmFragment<HomeViewModel>() {

    private lateinit var adapter: HomeAdapter

    private var dataList = arrayListOf<MovieBean>()

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_home

    override fun useLoadSir() = true

    override fun initView() {
        super.initView()
        mTopBar?.setTitle("首页")
        adapter = HomeAdapter(dataList).apply {
            setGridSpanSizeLookup { gridLayoutManager, viewType, position ->
                data.run {
                    val item = data[position]
                    when (item.itemType) {
                        HomeAdapter.HOME_HEADER -> 6
                        HomeAdapter.HOME_MOVIE -> 2
                        HomeAdapter.HOME_BANNER -> 6
                        HomeAdapter.HOME_TAB -> 1
                        else -> 2
                    }
                }
            }
        }
        rv_video_home.adapter = adapter
        rv_video_home.addItemDecoration(
            PinnedHeaderItemDecoration.Builder(HomeAdapter.HOME_HEADER)
                .disableHeaderClick(true)
                .create()
        )
    }

    override fun lazyLoadData() {
        loadNet()
    }

    override fun loadNet() {
        mViewModel.getMovieList()
    }

    override fun initObserve() {
        super.initObserve()
        mViewModel.run {
            liveMovieList.observe(viewLifecycleOwner, Observer {
                adapter.setList(it)
            })
        }
    }

}