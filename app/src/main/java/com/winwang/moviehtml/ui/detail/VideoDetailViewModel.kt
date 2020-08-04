package com.winwang.moviehtml.ui.detail

import androidx.lifecycle.MutableLiveData
import com.winwang.moviehtml.common.Constant
import com.winwang.moviehtml.http.ApiService
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import com.winwang.mvvm.http.RetrofitClient

/**
 *Created by WinWang on 2020/6/12
 *Description->
 */

class VideoDetailViewModel : BaseViewModel() {


    val playUrlEvent: MutableLiveData<String> = MutableLiveData() //电影列表数据


    fun getVideoDetail(path: String) {
        launch(block = {
            var response = RetrofitClient.getRetrofitByUrl(Constant.BASE_HOST)
                .create(ApiService::class.java).movieDetail(path)
            if (response.code == 200) {
                playUrlEvent.value = response.apiData().playUrl
            }
        })
    }


}