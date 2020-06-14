package com.winwang.moviehtml.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    /**
     * Jsoup爬虫有超时风险，尝试用Retrofit代替网络请求查看结果-》验证失败，一般网站都是通过agent-header-ip访问这些来做过滤的，所以普通请求并不能模拟成功
     */
    @GET("{path}")
    suspend fun movieRequest(@Path("path") path: String = ""): Call<String>
}