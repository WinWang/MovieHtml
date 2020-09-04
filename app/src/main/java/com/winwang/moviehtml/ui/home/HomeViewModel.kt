package com.winwang.moviehtml.ui.home

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.winwang.moviehtml.adapter.BannerBean
import com.winwang.moviehtml.bean.MovieBean
import com.winwang.moviehtml.common.Constant
import com.winwang.moviehtml.http.ApiService
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.http.RetrofitClient

/**
 *Created by WinWang on 2020/6/8
 *Description->
 */
class HomeViewModel : BaseViewModel() {

    val liveMovieList: MutableLiveData<MutableList<MovieBean>> = MutableLiveData() //电影列表数据

    private var movieList: ArrayList<MovieBean> = arrayListOf()
    private var bannerList: ArrayList<BannerBean> = arrayListOf()

    fun getMovieList() {
        movieList.clear()
        bannerList.clear()

        launch(
            block = {
                var response = RetrofitClient.getRetrofitByUrl(Constant.BASE_HOST)
                    .create(ApiService::class.java).movieHome()
                if (response.code == 200) {
                    liveMovieList.value = response.resultData()
                }
            },
            error = {
                LogUtils.e(it.toString())
            }
        )
    }


    fun testComponent(){
        println(123)
    }



}