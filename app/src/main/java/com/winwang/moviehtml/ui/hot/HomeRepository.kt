package com.winwang.moviehtml.ui.hot

import com.winwang.moviehtml.bean.MovieBean
import com.winwang.moviehtml.http.ApiService
import com.winwang.mvvm.http.BaseResponse

class HomeRepository(private val apiService: ApiService) {

    suspend fun getTestData(): BaseResponse<MutableList<MovieBean>> = apiService.movieHome()


}