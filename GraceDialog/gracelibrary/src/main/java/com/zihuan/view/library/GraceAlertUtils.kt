package com.zihuan.view.library

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.Fragment
import com.zihuan.view.library.source.GraceAlertView
import com.zihuan.view.library.source.GraceLoadingView


/***
 * 动画
 */
object GraceAlertUtils {
    /**
     * 左进左出
     */
    const val AnimLeft = 1

    /**
     * 右进右出
     */
    const val AnimRight = 2

    /**
     * 上进上出
     */
    const val AnimUp = 3

    /**
     * 下进下出
     */
    const val AnimDown = 4

    /**
     * 左入右出
     */
    const val AnimLeftRight = 5

    /**
     * 上入下出
     */
    const val AnimUpDown = 6

    /**
     * 淡入淡出
     */
    const val AnimFadeInOut = 7

    /**
     * 缩放出入
     */
    const val AnimZoomInOut = 8
}

/**
 * 自定义View
 */
inline fun <reified T : AlertBaseView> Context.graceAlert(noinline init: T.() -> Unit) =
        GraceAlertBuilder<T>(this).apply {
            val view = T::class.java.getDeclaredConstructor(Context::class.java).newInstance(this@graceAlert)
            setView(view)
            init(view)
        }


inline fun <reified T : AlertBaseView> Fragment.graceAlert(noinline init: T.() -> Unit) = requireContext().graceAlert(init)

inline fun <reified T : AlertBaseView> View.graceAlert(noinline init: T.() -> Unit) = context.graceAlert(init)

/**默认弹窗扩展方法*/
inline fun Context.defAlert(noinline init: GraceAlertView.() -> Unit) = graceAlert(init)


/**默认加载扩展方法*/
inline fun Context.loading(noinline init: GraceLoadingView.() -> Unit) = graceAlert(init)
inline fun Context.loading(show: Boolean = true): GraceAlertBuilder<GraceLoadingView> {
    var init: GraceLoadingView.() -> Unit = {
        showText = show
    }
    return graceAlert(init)
}

inline fun Fragment.loading(noinline init: GraceLoadingView.() -> Unit) = graceAlert(init)
inline fun Fragment.loading() = context!!.loading()

inline fun View.loading(noinline init: GraceLoadingView.() -> Unit) = graceAlert(init)
inline fun View.loading() = context!!.loading()

interface OnConfirmListener {
    fun onPositive(text: String, onClicked: () -> Unit)
    fun onNegative(text: String, onClicked: () -> Unit)
    fun onNeutral(text: String, onClicked: () -> Unit)
}


inline fun OnConfirmListener.okButton(text: String = GraceAlertManager.textOk, noinline handler: () -> Unit) = onPositive(text, handler)
inline fun OnConfirmListener.noButton(text: String = GraceAlertManager.textNo, noinline handler: () -> Unit) = onNegative(text, handler)
inline fun OnConfirmListener.otherButton(text: String = GraceAlertManager.textOther, noinline handler: () -> Unit) = onNeutral(text, handler)

internal fun <T : View> T.ZVShow(term: () -> Boolean) = apply { visibility = if (term()) View.VISIBLE else View.GONE }


/**
 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
 */
fun dip2px(context: Context, dpValue: Float): Float {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f)
}

/**
 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
 */
fun px2dip(context: Context, pxValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

/**
 * 获取屏幕宽度
 */
fun getScreenWidth(context: Activity): Int {
    val outMetrics = DisplayMetrics()
    context.windowManager.defaultDisplay.getRealMetrics(outMetrics)
    //    val heightPixel = outMetrics.heightPixels
    return outMetrics.widthPixels
}