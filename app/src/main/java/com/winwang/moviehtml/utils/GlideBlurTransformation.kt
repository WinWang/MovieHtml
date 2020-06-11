package com.winwang.moviehtml.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.CenterCrop


/**
 *Created by WinWang on 2020/6/11
 *Description->
 */
class GlideBlurTransformation(context: Context) : CenterCrop() {
    private val context: Context = context
    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap? {
        val bitmap = super.transform(pool, toTransform, outWidth, outHeight)
        return BitmapUtils.blurBitmap(
            context,
            bitmap,
            25f,
            (outWidth * 0.5).toInt(),
            (outHeight * 0.5).toInt()
        )
    }

}