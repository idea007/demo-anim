package com.dafay.demo.anim.ui.frg

import android.graphics.*
import android.os.Build
import com.dafay.demo.anim.R
import com.dafay.demo.anim.ui.weight.RoundDrawable
import kotlinx.android.synthetic.main.frg_test_custom_image.view.*

/**
 * Created by  idea on 2019-12-20$ 16:34$
 * des:
 */
class TestCustomImageFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.frg_test_custom_image
    }

    override fun onInitViews() {

        rootView.iv_custom_mark.post {
            rootView.iv_custom_mark.setImageBitmap(
                getCustomBitamp(
                    rootView.iv_custom_mark.width,
                    rootView.iv_custom_mark.height
                )
            )
        }

        initRepertView()
    }

    private fun initRepertView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            rootView.ll_repert_container.post({
                var roundDrawable = RoundDrawable(
                    rootView.ll_repert_container.context,
                    rootView.ll_repert_container.width,
                    rootView.ll_repert_container.height
                )
                rootView.ll_repert_container.foreground = roundDrawable
            })

        }
    }

    private fun getCustomBitamp(width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLACK

//        var path = Path()
//        path.addRect(RectF(0f, 0f, width.toFloat(), height.toFloat()), Path.Direction.CW)

        var path2 = Path()
        var rectF2 = RectF(0f, 0f, width.toFloat(), height.toFloat())

        var floatArray = floatArrayOf(30f, 30f, 30f, 30f, 0f, 0f, 0f, 0f)
        path2.addRoundRect(rectF2, floatArray, Path.Direction.CW)
        canvas.drawPath(path2, paint)
//        canvas.drawOval(RectF(0.0f, 0.0f, width.toFloat(), height.toFloat()), paint)
        return bitmap
    }

    private fun getCustomBitamp0(width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLACK
        canvas.drawOval(RectF(0.0f, 0.0f, width.toFloat(), height.toFloat()), paint)
        return bitmap
    }


}