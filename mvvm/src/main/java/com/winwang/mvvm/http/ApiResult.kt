package com.winwang.mvvm.http

data class BaseResponse<T>(val code: Int, val message: String, private val result: T) {
    fun resultData(): T {
        if (code == 200) {
            return result
        } else {
            throw ApiException(code, message)
        }
    }
}
