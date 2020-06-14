package com.winwang.moviehtml.ext

import com.blankj.utilcode.util.EncodeUtils
import com.winwang.moviehtml.common.Constant

fun String.regexPlayUrl(): String {

    return EncodeUtils.urlDecode(
        this.replace(Constant.TV_FLASH_URL, "").replace(Constant.TV_URL_END, "")
    )
}