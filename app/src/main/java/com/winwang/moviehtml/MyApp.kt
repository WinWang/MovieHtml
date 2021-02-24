package com.winwang.moviehtml

import androidx.lifecycle.ProcessLifecycleOwner
import com.winwang.moviehtml.common.ApplicationLifecycleListener
import com.winwang.moviehtml.di.appModule
import com.winwang.mvvm.base.App
import com.winwang.mvvm.common.CrashHandler
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
        CrashHandler.init()
        initProcessLifecycle()
    }

    private fun initProcessLifecycle() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleListener())
    }


}