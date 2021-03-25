package com.winwang.moviehtml

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.winwang.moviehtml.test.activity.ViewBindVMActivity
import com.winwang.moviehtml.ui.home.HomeFragment
import com.winwang.moviehtml.ui.hot.HotFragment
import com.winwang.moviehtml.ui.live.LiveFragment
import com.winwang.moviehtml.utils.Router
import com.winwang.mvvm.base.activity.BaseActivity
import com.winwang.mvvm.common.SimpleFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    var fragmentList = arrayListOf<Fragment>()

    override fun initViewData() {
        super.initViewData()
        nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> vp_home.currentItem = 0
                R.id.navigation_hot -> vp_home.currentItem = 1
                R.id.navigation_live -> vp_home.currentItem = 2
                R.id.navigation_mine -> vp_home.currentItem = 3
            }
            true
        }
        fragmentList?.run {
            add(HomeFragment())
            add(HotFragment())
            add(LiveFragment())
            add(LiveFragment())
        }
        val tabList = arrayListOf<CharSequence>(
            getString(R.string.home_tab),
            getString(R.string.type_tab),
            getString(R.string.live_tab),
            getString(R.string.my_tab)
        )
        vp_home?.run {
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) = Unit

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) = Unit

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> nav_view.selectedItemId = R.id.navigation_home
                        1 -> nav_view.selectedItemId = R.id.navigation_hot
                        2 -> nav_view.selectedItemId = R.id.navigation_live
                        3 -> nav_view.selectedItemId = R.id.navigation_mine
                    }
                }
            })
            adapter = SimpleFragmentPagerAdapter(supportFragmentManager, fragmentList, tabList)
            offscreenPageLimit = 4
            Router.newIntent(mContext)
                .to(ViewBindVMActivity::class.java)
                .launch()
        }

        /**
         * 测试自定义view+viewmodel 和Dialog的工作流程
         */
//        hot_view.init()
//        TestDialog().show(supportFragmentManager,"home")
//        hot_view.getMovieList()

    }


    /**
     * 修复Android Q版本的官方内存泄漏，必须在根Activity调用finishAfterTransition()-不能直接调用super
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBackPressed() {
        this.finishAfterTransition()
    }


}
