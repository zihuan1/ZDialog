package com.zihuan.view.library

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.FrameLayout

abstract class ZBaseView : FrameLayout, OnConfirmListener {
    constructor(context: Context) : super(context) {
        createView()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()

    protected var mZhListenerImp = ZDialogListenerImp()
    internal var dialog: Dialog? = null
    protected var textOk = ""
    protected var textNo = ""
    protected var textOther = ""
    protected lateinit var mView: View
    private fun createView() {
        mView = View.inflate(context, getLayoutId(), null)
        addView(mView)
        if (isInEditMode) return
        initView()
    }


    override fun onPositive(text: String, onClicked: () -> Unit) {
        textOk = text
        mZhListenerImp.setOkButton {
            onClicked()
            dialog?.dismiss()
        }
    }

    override fun onNegative(text: String, onClicked: () -> Unit) {
        textNo = text
        mZhListenerImp.setNoButton {
            onClicked()
            dialog?.dismiss()
        }
    }

    override fun onNeutral(text: String, onClicked: () -> Unit) {
        textOther = text
        mZhListenerImp.setOtherButton {
            onClicked()
            dialog?.dismiss()
        }
    }
}