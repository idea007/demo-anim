package com.dafay.demo.anim.utils

import android.text.TextUtils
import android.util.Log

/**
 * Created by  idea on 2019-11-21$ 18:54$
 * des: log utils
 */

object LogUtils {

    // 是否需要打印bug，可以在 gradle 配置debug 参数
    private var isDebug = true

    private val DEFALUT_TAG = "AnimAndTran"

    private val prefix = "------ "

    private fun buildLogHeader(): String {
        val ste = getLogStackTrackInfo()
        val buf = StringBuilder("[")
        buf.append(Thread.currentThread().name).append("]")
        if (ste == null) {
            return buf.append(":").toString()
        }
        buf.append(getSimpleClassName(ste!!.getClassName())).append(".").append(ste!!.getMethodName())
        if (ste!!.isNativeMethod()) {
            buf.append("(Native Method)")
        } else {
            val fName = ste!!.getFileName()
            if (fName == null) {
                buf.append("(Unknown Source)")
            } else {
                val lineNum = ste!!.getLineNumber()
                buf.append('(')
                buf.append(fName)
                if (lineNum >= 0) {
                    buf.append(':').append(lineNum)
                }
                buf.append("):")
            }
        }
        return buf.toString()
    }

    private fun getLogStackTrackInfo(): StackTraceElement? {
        val stackTrace = Thread.currentThread().stackTrace
        for (e in stackTrace) {
            if (e.className != "dalvik.system.VMStack"
                && e.className != "java.lang.Thread"
                && e.className != LogUtils::class.java.name
            ) {
                return e
            }
        }
        return null
    }

    private fun getSimpleClassName(pClassName: String): String {
        return pClassName.substring(pClassName.lastIndexOf(".") + 1)
    }

    private fun getTAG(): String {
        val ste = getLogStackTrackInfo()

        if (ste != null) {
            var name = getSimpleClassName(ste!!.getClassName())
            return if (TextUtils.isEmpty(name)) DEFALUT_TAG else name
        }

        return DEFALUT_TAG

    }


    @JvmStatic
    fun i(msg: String) {
        if (isDebug)
            Log.i(getTAG(), prefix + msg)
    }

    @JvmStatic
    fun d(msg: String) {
        if (isDebug)
            Log.d(getTAG(), prefix + msg)
    }

    @JvmStatic
    fun e(msg: String) {
        if (isDebug)
            Log.e(getTAG(), prefix + msg)
    }

    @JvmStatic
    fun w(msg: String) {
        if (isDebug)
            Log.w(getTAG(), prefix + msg)
    }

    @JvmStatic
    fun v(msg: String) {
        if (isDebug)
            Log.v(getTAG(), prefix + msg)
    }

    // 下面是传入自定义tag的函数
    @JvmStatic
    fun i(tag: String, msg: String) {
        if (isDebug)
            Log.i(tag, prefix + msg)
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        if (isDebug)
            Log.d(tag, prefix + msg)
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        if (isDebug)
            Log.e(tag, prefix + msg)
    }

    @JvmStatic
    fun e(tag: String, msg: String, e: Throwable) {
        if (isDebug)
            Log.e(tag, prefix + msg, e)
    }

    @JvmStatic
    fun w(tag: String, msg: String) {
        if (isDebug)
            Log.w(tag, prefix + msg)
    }

    @JvmStatic
    fun v(tag: String, msg: String) {
        if (isDebug)
            Log.v(tag, prefix + msg)
    }


}