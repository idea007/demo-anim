package com.idea.library.richeditor.utils

import android.content.Context

object DpUtils{

    fun dp2px(context: Context, dpValue:Float):Int{
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun dp2pxF(context: Context, dpValue:Float):Float{
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f)
    }

    fun px2dp(context: Context, pxValue:Float):Int{
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun sp2px(context: Context, spValue:Float):Int{
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    fun px2sp(context: Context, pxValue:Float):Int{
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    fun screenWidth(context: Context):Int = context.resources.displayMetrics.widthPixels
    fun screenHeight(context: Context):Int = context.resources.displayMetrics.heightPixels

}