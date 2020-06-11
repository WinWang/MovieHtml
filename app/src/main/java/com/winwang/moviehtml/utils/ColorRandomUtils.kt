package com.winwang.moviehtml.utils

/**
 *Created by WinWang on 2020/6/11
 *Description->
 */
object ColorRandomUtils {

    fun generateColor(position: Int): String {
        when (position % 13) {
            0 -> return "#ef5b9c"
            1 -> return "#843900"
            2 -> return "#6d8346"
            3 -> return "#494e8f"
            4 -> return "#845538"
            5 -> return "#7a1723"
            6 -> return "#e0861a"
            7 -> return "#4d4f36"
            8 -> return "#2468a2"
            9 -> return "#733a31"
            10 -> return "#454926"
            11 -> return "#905d1d"
            12 -> return "#00a6ac"
            else -> return "#f47920"
        }
    }


}