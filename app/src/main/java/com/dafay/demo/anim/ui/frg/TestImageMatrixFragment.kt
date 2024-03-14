package com.dafay.demo.anim.ui.frg

import android.graphics.Matrix
import android.widget.ImageView
import android.widget.SeekBar
import com.idea.android.animandtran.R
import com.dafay.demo.anim.utils.ImageViewUtils
import com.dafay.demo.anim.utils.MatrixUtils
import kotlinx.android.synthetic.main.frg_test_image_matrix.view.*

/**
 * Created by  idea on 2019-12-20$ 16:34$
 * des:
 */
class TestImageMatrixFragment : BaseFragment() {


    override fun getLayoutId(): Int {
        return R.layout.frg_test_image_matrix
    }


    override fun onInitViews() {
        mRootView.iv_origin_image.scaleType = ImageView.ScaleType.CENTER_CROP
        mRootView.iv_origin_image.setImageResource(R.mipmap.img_29)

        mRootView.iv_origin_image.post(object : Runnable {
            override fun run() {
                mViewCenterX = mRootView.iv_origin_image.width / 2f
                mViewCenterY = mRootView.iv_origin_image.height / 2f

                originMatrix = MatrixUtils.getImageViewMatrix(mRootView.iv_origin_image)
                originMatrix?.getValues(matrixValues)

            }
        })



        bindListener()

    }


    private var originMatrix: Matrix? = null
    private val matrixValues = FloatArray(9)

    private var dx = 0f
    private var dy = 0f
    private var zoom = 0f
    private var rotation = 0f

    private var mViewCenterX = 0f
    private var mViewCenterY = 0f

    private fun bindListener() {
        mRootView.sb_translate_x.max = 300
        mRootView.sb_translate_x.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                var thumDx = progress.toFloat() - dx
                dx = progress.toFloat()
                originMatrix?.postTranslate(thumDx, 0f)
                ImageViewUtils.animateTransform(mRootView.iv_origin_image, originMatrix)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        mRootView.sb_translate_y.max = 300
        mRootView.sb_translate_y.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                var thumDy = progress.toFloat() - dy
                dy = progress.toFloat()
                originMatrix?.postTranslate(0f, thumDy)
                ImageViewUtils.animateTransform(mRootView.iv_origin_image, originMatrix)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        mRootView.sb_rotate.max = 180
        mRootView.sb_rotate.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var thumRotation = progress - rotation
                rotation = progress.toFloat()
                originMatrix?.postRotate(thumRotation, mViewCenterX, mViewCenterY)

                ImageViewUtils.animateTransform(mRootView.iv_origin_image, originMatrix)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


        mRootView.sb_zoom.setProgress(10)
        mRootView.sb_zoom.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                var thumMatix = Matrix()
                thumMatix.setValues(matrixValues)
                thumMatix?.postTranslate(dx, dy)
                thumMatix?.postRotate(rotation, mViewCenterX, mViewCenterY)
                thumMatix?.postScale(
                    progress / 10f,
                    progress / 10f,
                    mViewCenterX,
                    mViewCenterY
                )
                ImageViewUtils.animateTransform(mRootView.iv_origin_image, thumMatix)


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

    }


}