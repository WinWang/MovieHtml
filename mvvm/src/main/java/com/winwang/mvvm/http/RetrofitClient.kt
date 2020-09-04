package com.winwang.mvvm.http

import android.text.TextUtils
import com.winwang.mvvm.common.UrlConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(UrlConfig.BASE_MOVIE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofitByUrl(url: String = UrlConfig.BASE_HOST): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//    val apiService: ApiService = retrofit.create(ApiService::class.java)
//
//    fun apiServiceByUrl(url: String = Constant.BASE_MOVIE_URL): ApiService {
//        return getRetrofitByUrl(url).create(ApiService::class.java)
//    }


}