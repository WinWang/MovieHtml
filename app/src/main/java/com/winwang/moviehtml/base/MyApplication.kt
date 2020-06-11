package com.winwang.moviehtml.base

import android.app.Application
import com.kingja.loadsir.core.LoadSir
import com.winwang.moviehtml.common.ActivityLifecycleCallbacksAdapter
import com.winwang.moviehtml.loadsir.*
import com.winwang.moviehtml.utils.ActivityManager
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits

/**
 *Created by WinWang on 2020/6/5
 *Description->
 */
class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
    }


    private fun init() {
        registerAppLifecycler()
        initAutoSize()
        initLoadSir()
    }

    private fun initLoadSir() {
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback()) //添加各种状态页
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .addCallback(TimeoutCallback())
            .addCallback(CustomCallback())
            .setDefaultCallback(LoadingCallback::class.java) //设置默认状态页
            .commit()
    }

    private fun initAutoSize() {
        AutoSizeConfig.getInstance().getUnitsManager()
            .setSupportDP(false)
            .setSupportSP(false)
            .setSupportSubunits(Subunits.PT);
    }

    private fun registerAppLifecycler() {
        registerActivityLifecycleCallbacks(
            ActivityLifecycleCallbacksAdapter(
                onActivityCreated = { activity, savedInstanceState ->
                    ActivityManager.activityMap.add(activity)
                },

                onActivityDestroyed = { activity ->
                    ActivityManager.activityMap.remove(activity)
                }

            )
        )
    }


}