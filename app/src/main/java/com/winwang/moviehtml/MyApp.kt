package com.winwang.moviehtml

import com.blankj.utilcode.util.LogUtils
import com.winwang.moviehtml.di.appModule
import com.winwang.mvvm.base.App
import org.koin.core.context.startKoin

/**
 *Created by WinWang on 2020/8/4
 *Description->
 */
class MyApp : App() {

    override fun initMethod() {
        startKoin {
            modules(appModule)
        }
    }

}