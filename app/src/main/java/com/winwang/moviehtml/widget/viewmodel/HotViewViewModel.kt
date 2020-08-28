package com.winwang.moviehtml.widget.viewmodel

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.winwang.moviehtml.adapter.BannerBean
import com.winwang.moviehtml.bean.MovieBean
import com.winwang.moviehtml.common.Constant
import com.winwang.moviehtml.http.ApiService
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.http.RetrofitClient

/**
 *Created by WinWang on 2020/8/26
 *Description->
 */
class HotViewViewModel : BaseViewModel() {

    val movieListLiveData: MutableLiveData<MutableList<MovieBean>> = MutableLiveData() //电影列表数据

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
                    movieListLiveData.value = response.apiData()
                }
            },
            error = {
                LogUtils.e(it.toString())
            }
        )
    }


}