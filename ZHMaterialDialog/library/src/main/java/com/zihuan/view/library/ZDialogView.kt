package com.zihuan.view.library

import android.content.Context
import kotlinx.android.synthetic.main.layout_z_def_dialog.view.*

/**
 * 默认提供的view
 */
class ZDialogView(context: Context) : ZBaseView(context) {


    //    标题
    var title = ZDialogManager.textTitle
    //    内容
    var content = ""
    var type = ZHDialogType.DEFAULT
    //是否显示标题线
    var titleDivider = false
    //线段颜色 背景色
    var dividerColor = ZDialogManager.textColor
        get() = context.run { resources.getColor(field) }
    var buttonTextColor = ZDialogManager.buttonColor
        get() = context.run { resources.getColor(field) }
    var bgcolor = ZDialogManager.backgroundColor
        get() = context.run { resources.getColor(field) }

    var okButtonShow = false
    var noButtonShow = false
    var otherButtonShow = false

    override fun getLayoutId() = R.layout.layout_z_def_dialog
    override fun initView() {
        tv_ok.setOnClickListener {
            mZhListenerImp.performOk()
        }
        tv_no.setOnClickListener {
            mZhListenerImp.performNo()
        }
        tv_other.setOnClickListener {
            mZhListenerImp.performOther()
        }

    }

    override fun initData() {
        when (type) {
            ZHDialogType.DEFAULT -> {
                title_lin.visibility = GONE
                et_name.visibility = GONE
            }
            ZHDialogType.EDIT -> {
                title_lin.visibility = VISIBLE
                et_name.visibility = VISIBLE
            }
        }
        tv_ok.setTextColor(buttonTextColor)
        tv_no.setTextColor(buttonTextColor)
        tv_title.setTextColor(buttonTextColor)
        title_lin.setBackgroundColor(dividerColor)
        rl_background.setBackgroundColor(bgcolor)
        tv_title.text = title
        tv_content.text = content
        tv_ok.ZVShow { textOk.isNotEmpty() || noButtonShow }.text = textOk
        tv_no.ZVShow { textNo.isNotEmpty() || noButtonShow }.text = textNo
        tv_other.ZVShow { textOther.isNotEmpty() || otherButtonShow }.text = textOther
        title_lin.visibility = if (titleDivider) VISIBLE else GONE
    }

}