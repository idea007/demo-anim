package com.dafay.demo.anim.ui.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;


/**
 * Created by idea on 2018/4/3.
 */

@SuppressLint("AppCompatCustomView")
public class CircularImageView extends ImageView {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOutlineProvider(CIRCULAR_OUTLINE);
        setClipToOutline(true);
    }


    public final ViewOutlineProvider CIRCULAR_OUTLINE = new ViewOutlineProvider() {
        @Override
        public void getOutline(View view, Outline outline) {
            outline.setOval(view.getPaddingLeft(),
                    view.getPaddingTop(),
                    view.getWidth() - view.getPaddingRight(),
                    view.getHeight() - view.getPaddingBottom());
        }
    };
}

