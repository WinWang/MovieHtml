package com.winwang.mvvm.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.LogUtils
import com.winwang.mvvm.R
import kotlinx.android.synthetic.main.fragment_progress_dialog.*

/**
 *Created by WinWang on 2020/6/8
 *Description->为了在View中也能使用Dialog，暂时放弃DialogFragment，使用Dialog替代
 */
class LoadingDialog : DialogFragment() {

    private var messageResId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_progress_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvMessage.text = getString(messageResId ?: R.string.loading)
    }

    /**
     * 解决DialogFragment造成的内存泄露
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.setOnShowListener(null)
        dialog?.setOnCancelListener(null)
        dialog?.setOnDismissListener(null)
    }


    fun show(
        fragmentManager: FragmentManager,
        messageResId: Int? = null,
        isCancelable: Boolean = false
    ) {
        this.messageResId = messageResId
        this.isCancelable = isCancelable
        show(fragmentManager, "progressDialogFragment")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.d("加载loading关闭")
    }


}