package com.winwang.moviehtml.di

import com.winwang.moviehtml.http.ApiService
import com.winwang.moviehtml.http.HomeService
import com.winwang.moviehtml.ui.hot.HomeRepository
import com.winwang.moviehtml.ui.hot.HotViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * 三者的关系是一层一层依赖，中间的查找在koin里面通过get（）查找获取 -viewmodle依赖Respository --> Respository依赖remoteModule的ApiService
 */


//viewmodel注入管理类
val viewModelModule = module {
    viewModel { HotViewModel(get()) }

}

val reposModule = module {
    //factory 每次注入时都重新创建一个新的对象
    factory { HomeRepository(get()) }
}

//远程请求数据类
val remoteModule = module {
    //single 单列注入
    single<ApiService> { HomeService }
}

val appModule = listOf(viewModelModule, reposModule, remoteModule)

