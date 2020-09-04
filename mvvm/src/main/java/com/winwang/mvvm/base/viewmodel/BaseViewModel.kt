package com.winwang.mvvm.base.viewmodel

import android.os.NetworkOnMainThreadException
import androidx.lifecycle.*
import com.google.gson.JsonParseException
import com.winwang.mvvm.enums.ViewStatusEnum
import com.winwang.mvvm.R
import com.winwang.mvvm.base.App
import com.winwang.mvvm.ext.showToast
import kotlinx.coroutines.*
import java.net.ConnectException
import java.net.SocketTimeoutException


typealias Block<T> = suspend () -> T     //可以在别名类指定类型，例如suspend CoroutineScope.() -> Unit -----但是此时的block不需要调用invoke 了，直接block（）
typealias Error = suspend (e: Exception) -> Unit
typealias Cancel = suspend (e: Exception) -> Unit
typealias EmitBlock<T> = suspend LiveDataScope<T>.() -> T

/**
 *Created by WinWang on 2020/6/8
 *Description->
 */
open class BaseViewModel : ViewModel() {

    val loginStatusInvalid: MutableLiveData<Boolean> = MutableLiveData()

    //封装页面状态的LiveData
    val viewStatus: MutableLiveData<Enum<ViewStatusEnum>> = MutableLiveData()


    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @param error 错误时执行
     * @param cancel 错误时执行
     * @param handleError 是否处理异常
     * @return Job
     */
    protected fun launch(
        block: Block<Unit>,
        error: Error? = null,
        cancel: Cancel? = null,
        handleError: Boolean = true
    ): Job {
        return viewModelScope.launch {
            try {
                block.invoke()
                viewStatus.value = ViewStatusEnum.SUCCESS
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> {
                        cancel?.invoke(e)
                    }
                    else -> {
                        if (handleError) {
                            onError(e)
                        }
                        error?.invoke(e)
                    }
                }
            }
        }
    }


    /**
     * @param dispatcher  设置线程，这里默认主线程是因为默认的方法通过suspend挂起有自身线程封闭机制，所以不需要创建多余的线程，预留字段，是为了给自己Jsoup框架子线程执行-防止崩溃
     * @param block 协程中执行
     * @return Deferred<T>
     */
    protected fun <T> async(
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        block: Block<T>
    ): Deferred<T> {
        return viewModelScope.async(dispatcher) { block.invoke() }
    }


    /**
     * 取消协程
     * @param job 协程job
     */
    protected fun cancelJob(job: Job?) {
        if (job != null && job.isActive && !job.isCompleted && !job.isCancelled) {
            job.cancel()
        }
    }

    /**
     * 省去每次创建liveData的烦恼，利用liveData的包装创建，直接传入block发送道对应的页面
     */
    fun <T> emit(cancel: Cancel? = null, block: EmitBlock<T>): LiveData<T> = liveData {
        try {
            emit(block())
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> cancel?.invoke(e)
                else -> onError(e)
            }
        }
    }


    /**
     * 统一处理错误
     * @param e 异常
     */
    private fun onError(e: Exception) {
        when (e) {
            is ConnectException -> {
                // 连接失败
                App.instance.showToast(
                    App.instance.getString(R.string.network_connection_failed)
                )
                viewStatus.value = ViewStatusEnum.NETWORKERROR
            }
            is SocketTimeoutException -> {
                // 请求超时
                App.instance.showToast(
                    App.instance.getString(R.string.network_request_timeout)
                )
                viewStatus.value = ViewStatusEnum.NETWORKERROR
            }
            is JsonParseException -> {
                // 数据解析错误
                App.instance.showToast(
                    App.instance.getString(R.string.api_data_parse_error)
                )
                viewStatus.value = ViewStatusEnum.ERROR
            }
            is NetworkOnMainThreadException -> {
                App.instance.showToast(
                    App.instance.getString(R.string.network_thread_exception)
                )
                viewStatus.value = ViewStatusEnum.ERROR
            }
            else -> {
                // 其他错误
                e.message?.let { App.instance.showToast(it) }
                viewStatus.value = ViewStatusEnum.ERROR
            }
        }
    }


}