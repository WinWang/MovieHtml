package com.winwang.moviehtml.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.winwang.moviehtml.R

/**
 *Created by WinWang on 2020/6/4
 *Description->
 */
object GlideUtils {

    /**
     * 加载普通图片
     */
    @SuppressLint("CheckResult")
    fun loadNetImage(imageUrl: String, view: ImageView) {
        val requestOptions = RequestOptions()
        requestOptions.error(R.drawable.placeholder)
        requestOptions.placeholder(R.drawable.placeholder)
        Glide.with(view).load(imageUrl).apply(requestOptions).into(view)
    }


    /**
     * 圆角图片
     */
    @SuppressLint("CheckResult")
    fun loadRadiusNetImage(imageUrl: String, view: ImageView) {
        val roundedCorners = RoundedCorners(10)
        val requestOptions = RequestOptions.bitmapTransform(roundedCorners)
        requestOptions.error(R.drawable.placeholder)
        requestOptions.placeholder(R.drawable.placeholder)
        Glide.with(view).load(imageUrl).apply(requestOptions).into(view)
    }


    /**
     * 加载普通图片
     */
    @SuppressLint("CheckResult")
    fun loadGasNetImage(imageUrl: String, view: ImageView) {
        val requestOptions = RequestOptions.bitmapTransform(GlideBlurTransformation(view.context))
        requestOptions.error(R.drawable.placeholder)
        requestOptions.placeholder(R.drawable.placeholder)
        Glide.with(view).load(imageUrl).apply(requestOptions).into(view)
    }


}