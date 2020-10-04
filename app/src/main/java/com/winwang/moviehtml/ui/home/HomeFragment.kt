package com.winwang.moviehtml.ui.home

import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration
import com.winwang.moviehtml.R
import com.winwang.moviehtml.adapter.HomeAdapter
import com.winwang.moviehtml.bean.MovieBean
import com.winwang.moviehtml.ui.detail.VideoDetailActivity
import com.winwang.moviehtml.utils.Router
import com.winwang.mvvm.base.fragment.BaseVmFragment
import com.winwang.mvvm.common.KVutils
import kotlinx.android.synthetic.main.fragment_home.*


/**
 *Created by WinWang on 2020/6/8
 *Description->
 */
class HomeFragment : BaseVmFragment<HomeViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var adapter: HomeAdapter

    private var dataList = arrayListOf<MovieBean>()

    override fun getLayoutId() = R.layout.fragment_home

    override fun useLoadSir() = true

    override fun initView() {
        super.initView()
        mTopBar?.setTitle("首页")
        showDialogLoading(null)
        refresh_home.setColorSchemeColors(resources.getColor(R.color.colorAccent))
        refresh_home.setOnRefreshListener(this)
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
            setOnItemClickListener { adapter, view, position ->
                val item: MovieBean = adapter.data[position] as MovieBean
                when (item.itemType) {
                    HomeAdapter.HOME_TAB -> {

                    }

                    HomeAdapter.HOME_HEADER -> {

                    }

                    HomeAdapter.HOME_MOVIE -> {
                        Router.newIntent(mContext)
                            .to(VideoDetailActivity::class.java)
                            .putString(VideoDetailActivity.VIDEO_DETAIL_KEY, item.linkUrl)
                            .putString(VideoDetailActivity.VIDEO_NAME, item.movieName)
                            .putString(VideoDetailActivity.VIDEO_COVER, item.coverUrl)
                            .launch()
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
                adapter.setNewInstance(it)
                hideLoading()
            })
        }

    }

    override fun onRefresh() {
        loadNet()
    }

    override fun hideRefresh() {
        super.hideRefresh()
        refresh_home.isRefreshing = false
    }


}