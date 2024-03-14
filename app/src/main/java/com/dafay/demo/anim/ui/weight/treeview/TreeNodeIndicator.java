package com.dafay.demo.anim.ui.weight.treeview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.idea.android.animandtran.R;
import com.dafay.demo.anim.data.Node;
import com.mikepenz.iconics.IconicsColor;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.IconicsSize;
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial;


public class TreeNodeIndicator extends RelativeLayout {

    public static final int STATE_EXPAND = 0x01;
    public static final int STATE_COLLAPSE = 0x02;

    private RelativeLayout mRlytNavi;
    private View mTopLine, mBottomLine;
    private ImageView mIvMiddle;

    private int mLevel = -1, mState;
    private boolean isLast, isStart;

    private Drawable mDrawablePlusBig;
    private Drawable mDrawableMinusBig;
    private Drawable mDrawablePlusSmall;
    private Drawable mDrawableMinusSamll;
    private Drawable mDrawableLeaf;

    public TreeNodeIndicator(Context context) {
        super(context);
        inflateView(context);
    }

    public TreeNodeIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);
    }

    public TreeNodeIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView(context);
    }

    private void inflateView(Context context) {

//        mDrawablePlusBig = getResources().getDrawable(R.mipmap.ic_add_circle_outline_black_48dp);
//        mDrawableMinusBig = getResources().getDrawable(R.mipmap.ic_remove_circle_outline_black_48dp);
//        mDrawablePlusSmall = getResources().getDrawable(R.mipmap.ic_add_circle_outline_black_48dp);
//        mDrawableMinusSamll = getResources().getDrawable(R.mipmap.ic_remove_circle_outline_black_48dp);
//        mDrawableLeaf = getResources().getDrawable(R.mipmap.ic_panorama_fish_eye_black_36dp);


        mDrawablePlusBig = new IconicsDrawable(context, GoogleMaterial.Icon.gmd_add_circle_outline)
                .color(IconicsColor.colorInt(Color.BLACK))
                .size(IconicsSize.dp(18));
        mDrawableMinusBig = new IconicsDrawable(context, GoogleMaterial.Icon.gmd_remove_circle_outline).color(IconicsColor.colorInt(Color.BLACK))
                .size(IconicsSize.dp(18));
        mDrawablePlusSmall = new IconicsDrawable(context, GoogleMaterial.Icon.gmd_add_circle_outline).color(IconicsColor.colorInt(Color.BLACK))
                .size(IconicsSize.dp(14));
        mDrawableMinusSamll = new IconicsDrawable(context, GoogleMaterial.Icon.gmd_remove_circle_outline).color(IconicsColor.colorInt(Color.BLACK))
                .size(IconicsSize.dp(14));
        mDrawableLeaf = new IconicsDrawable(context, GoogleMaterial.Icon.gmd_panorama_fish_eye).color(IconicsColor.colorInt(Color.BLACK))
                .size(IconicsSize.dp(12));


        LayoutInflater.from(getContext()).inflate(R.layout.view_tree_node_indicator, this,
                true);
        mTopLine = findViewById(R.id.top_line);
        mBottomLine = findViewById(R.id.bottom_line);
        mIvMiddle = (ImageView) findViewById(R.id.middle_img);
    }

    public void reset() {
        mLevel = -1;
        mState = 0;
        isLast = false;
        mTopLine.setVisibility(View.VISIBLE);
        mBottomLine.setVisibility(View.VISIBLE);

    }

    public void setLevel(Node node) {
        int level = node.getLevel();
        if (level == mLevel)
            return;
        mLevel = level;

        switch (mLevel) {
            case 0:
                mIvMiddle.setImageDrawable(mDrawablePlusBig);
                mTopLine.setVisibility(View.INVISIBLE);
                break;
            case 1:
                mIvMiddle.setImageDrawable(mDrawablePlusSmall);
                break;
            case 2:
                mIvMiddle.setImageDrawable(mDrawableLeaf);
                break;
        }
    }

    public void setState(Node node) {
        int state = node.isExpand() ? TreeNodeIndicator.STATE_EXPAND : TreeNodeIndicator.STATE_COLLAPSE;
        mState = state;
        switch (mState) {
            case STATE_EXPAND:
                mBottomLine.setVisibility(View.VISIBLE);
                if (mLevel == 1) {
                    mIvMiddle.setImageDrawable(mDrawableMinusSamll);
                } else {
                    mIvMiddle.setImageDrawable(mDrawableMinusBig);
                }
                break;
            case STATE_COLLAPSE:
                if (isLast)
                    mBottomLine.setVisibility(View.INVISIBLE);
                if (mLevel == 0) {
                    mTopLine.setVisibility(View.INVISIBLE);
                    mBottomLine.setVisibility(View.INVISIBLE);
                }
                if (mLevel == 2) {
                    mIvMiddle.setImageDrawable(mDrawableLeaf);
                    return;
                }

                if (mLevel == 1) {
                    mIvMiddle.setImageDrawable(mDrawablePlusSmall);
                } else {
                    mIvMiddle.setImageDrawable(mDrawablePlusBig);
                }
                break;
        }
        if (node.isLeaf()) {
            mIvMiddle.setImageDrawable(mDrawableLeaf);
        }
    }

    public void isLast(boolean isLast) {
        this.isLast = isLast;
    }

    public void isStart(boolean isStart) {
        this.isStart = isStart;
        if (this.isStart)
            mTopLine.setVisibility(View.INVISIBLE);
    }

}
