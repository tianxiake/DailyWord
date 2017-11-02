package com.nebulaera.dailyword.view;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;


/**
 * @author Barry 2017/11/2
 */

public class CircleImageView extends AppCompatImageView {

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int measureHeight = MeasureSpec.getSize(widthMeasureSpec);
//        MeasureSpec.getSize(heightMeasureSpec)
    }
}
