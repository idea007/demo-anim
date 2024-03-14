package com.dafay.demo.anim.ui.anim.custom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.util.Property;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 */
public class ChangeTextTransition extends Transition {

    protected static final String PROPNAME_TEXTSIZE = "ChangeTextTransition::textSize";
    protected static final String PROPNAME_TEXTCOLOR = "ChangeTextTransition::textColor";


    private float startTextSize;
    private float endTextSize;

    private int startTextColor;
    private int endTextColor;

    private boolean isEnter = true;

    public ChangeTextTransition(float startTextSize, float endTextSize, int startTextColor, int endTextColor) {
//        addTarget(TextView.class);
        this.startTextSize = startTextSize;
        this.endTextSize = endTextSize;
        this.startTextColor = startTextColor;
        this.endTextColor = endTextColor;
    }


    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        if (isEnter) {
            captureValues(transitionValues, startTextSize, startTextColor);
        } else {
            captureValues(transitionValues, endTextSize, endTextColor);
        }
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        if (isEnter) {
            captureValues(transitionValues, endTextSize, endTextColor);
        } else {
            captureValues(transitionValues, startTextSize, startTextColor);
        }
    }

    protected void captureValues(TransitionValues value, float textSize, int textColor) {
        value.values.put(PROPNAME_TEXTSIZE, textSize);
        value.values.put(PROPNAME_TEXTCOLOR, textColor);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        Log.w("----", "---- createAnimator");
        final TextView view = (TextView) endValues.view;
        if (view == null) {
            return null;
        }

        view.setPivotX(0f);
        view.setPivotY(0f);

        float startTextSize = (float) startValues.values.get(PROPNAME_TEXTSIZE);
        final float endTextSize = (float) endValues.values.get(PROPNAME_TEXTSIZE);
        ObjectAnimator textSizeAnimator = ObjectAnimator.ofFloat(view, new TextSizeProperty(), startTextSize, endTextSize);

        int startTextColor = (int) startValues.values.get(PROPNAME_TEXTCOLOR);
        int endTextColor = (int) endValues.values.get(PROPNAME_TEXTCOLOR);
        ObjectAnimator textColorAnimator = ObjectAnimator.ofArgb(view, new TextColorProperty(), startTextColor, endTextColor);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(textSizeAnimator, textColorAnimator);

        return animatorSet;

    }

    public void setIsEnter(boolean b) {
        this.isEnter = b;
    }


    private class TextSizeProperty extends Property<TextView, Float> {

        public TextSizeProperty() {
            super(Float.class, "textSize");
        }

        @Override
        public void set(TextView object, Float value) {
            object.setTextSize(TypedValue.COMPLEX_UNIT_PX, value);
        }

        @Override
        public Float get(TextView object) {
            return object.getTextSize();
        }
    }

    private class TextColorProperty extends Property<TextView, Integer> {

        public TextColorProperty() {
            super(Integer.class, "textColor");
        }

        @Override
        public void set(TextView object, Integer value) {
            object.setTextColor(value);
        }

        @Override
        public Integer get(TextView object) {
            return object.getCurrentTextColor();
        }
    }
}
