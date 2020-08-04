package com.winwang.moviehtml.http

import com.winwang.moviehtml.common.Constant
import com.winwang.mvvm.http.RetrofitClient


object HomeService :
    ApiService by RetrofitClient.getRetrofitByUrl(Constant.BASE_HOST).create(ApiService::class.java)