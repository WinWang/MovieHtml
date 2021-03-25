package com.winwang.mvvm.base.activity

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.winwang.mvvm.base.IView
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

/**
 *Created by WinWang on 2020/6/8
 *Description->使用了DataBinding的普通基类
 */
abstract class BaseVBActivity<VB : ViewBinding> : BaseActivity() {

    lateinit var mbinding: VB

    override fun setContentLayout() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val actualTypeArguments = type.actualTypeArguments
            val clazz =
                (if (actualTypeArguments.size > 1) actualTypeArguments[1] else actualTypeArguments[0]) as Class<VB>
            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
            mbinding = method.invoke(null, layoutInflater) as VB
            setContentView(mbinding.root)
        }
    }


}