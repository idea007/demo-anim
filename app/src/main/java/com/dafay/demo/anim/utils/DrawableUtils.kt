//package com.dafay.demo.anim.utils
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.Canvas
//import android.graphics.drawable.Animatable
//import android.graphics.drawable.Drawable
//import android.graphics.drawable.LayerDrawable
//import android.graphics.drawable.TransitionDrawable
//import androidx.annotation.DrawableRes
//import androidx.core.content.ContextCompat
//import com.bumptech.glide.load.resource.gif.GifDrawable
///**
// * Created by  idea on 2019/12/24$ 下午8:45$
// * des:
// */
//object DrawableUtils {
//
//
//
//
//    fun Drawable.toBitmap(): Bitmap {
//        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(bitmap)
//        setBounds(0, 0, canvas.width, canvas.height)
//        draw(canvas)
//        return bitmap
//    }
//
//    fun drawableToBitmap(context: Context, @DrawableRes drawableId: Int) =
//        ContextCompat.getDrawable(context, drawableId)?.toBitmap()
//
//    fun Drawable.isAnimated() = this is Animatable
//
//    val LayerDrawable.layers: List<Drawable>
//        get() = (0 until numberOfLayers).map { getDrawable(it) }
//
//    /**
//     * If the [Drawable] is a gif, it returns it as [GifDrawable]. Returns null otherwise.
//     */
//    fun Drawable.asGif(): GifDrawable? {
//        var gif: GifDrawable? = null
//        if (this is GifDrawable) {
//            return this
//        } else if (this is TransitionDrawable) {
//            // we fade in images on load which uses a TransitionDrawable; check its
//            // layers
//            val fadingIn = this
//            for (i in 0 until this.numberOfLayers) {
//                if (fadingIn.getDrawable(i) is GifDrawable) {
//                    gif = fadingIn.getDrawable(i) as GifDrawable
//                    break
//                }
//            }
//        }
//        return gif
//    }
//}