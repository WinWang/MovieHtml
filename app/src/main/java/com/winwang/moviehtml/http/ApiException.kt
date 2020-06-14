package com.winwang.moviehtml.http

class ApiException(var code: Int, override var message: String) : RuntimeException()