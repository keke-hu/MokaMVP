package com.cdwx.moka.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.cdwx.moka.R;

/**
 * Created by KeKe on 2016/8/16.
 */
public class CouponDisplayView extends LinearLayout {

    private Paint mPaint;
    /**
     * 圆间距
     */
    private float gap = 10;
    /**
     * 半径
     */
    private float radius = 14;
    /**
     * 圆数量
     */
    private int circleNum;

    private float remain;

    private int circle_color;


    public CouponDisplayView(Context context) {
        super(context);
    }

    public CouponDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CouponDisplayView);
            gap = ta.getDimension(R.styleable.CouponDisplayView_coupon_gap, 10);
            radius = ta.getDimension(R.styleable.CouponDisplayView_coupon_radius, 14);
            circle_color = ta.getColor(R.styleable.CouponDisplayView_coupon_circle_color, Color.BLACK);
            ta.recycle();
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(circle_color);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {
            remain = (int) (w - gap) % (2 * radius + gap);
        }
        circleNum = (int) ((w - gap) / (2 * radius + gap));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < circleNum; i++) {
            float x = gap + radius + remain / 2 + ((gap + radius * 2) * i);
            canvas.drawCircle(x, getHeight(), radius, mPaint);
        }
    }


    public CouponDisplayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
