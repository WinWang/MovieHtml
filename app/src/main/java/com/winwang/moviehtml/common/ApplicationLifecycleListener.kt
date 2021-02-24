package com.winwang.moviehtml.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.LogUtils


/**
 *Created by WinWang on 2020/12/28
 *Description->系统Application的生命周期的回调
 */
class ApplicationLifecycleListener() : LifecycleObserver {

    val TAG = "ApplicationLifecycleListener"

    /**
     * ON_CREATE 在应用程序的整个生命周期中只会被调用一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        LogUtils.d(TAG, "Lifecycle.Event.ON_CREATE")
    }

    /**
     * 应用程序出现到前台时调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        LogUtils.d(TAG, "Lifecycle.Event.ON_START")
    }

    /**
     * 应用程序出现到前台时调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        LogUtils.d(TAG, "Lifecycle.Event.ON_RESUME")
    }

    /**
     * 应用程序退出到后台时调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        LogUtils.d(TAG, "Lifecycle.Event.ON_PAUSE")
    }

    /**
     * 应用程序退出到后台时调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        LogUtils.d(TAG, "Lifecycle.Event.ON_STOP")
    }

    /**
     * 永远不会被调用到，系统不会分发调用ON_DESTROY事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        LogUtils.d(TAG, "Lifecycle.Event.ON_DESTROY")
    }
}