package com.cdwx.moka.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.cdwx.moka.R;

public class PageIndicatorView extends View {
    private int mCurrentPage = -1;
    private int mTotalPage = 0;
    private int type = 0;// 0:右下角，1：中间

    public PageIndicatorView(Context context) {
        super(context);
    }

    public PageIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTotalPage(int nPageNum) {
        mTotalPage = nPageNum;
        if (mCurrentPage >= mTotalPage)
            mCurrentPage = mTotalPage - 1;
    }

    public void settype(int type) {
        this.type = type;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(int nPageIndex) {
        if (nPageIndex < 0 || nPageIndex >= mTotalPage)
            return;

        if (mCurrentPage != nPageIndex) {
            mCurrentPage = nPageIndex;
            this.invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);

        Rect r = new Rect();
        this.getDrawingRect(r);

        int iconWidth = 15;
        int iconHeight = 15;
        int space = 12;
        int x = 0;
        int y = 0;
        if (type == 0) {
            x = (r.width() - (iconWidth * mTotalPage + space * (mTotalPage - 1)));
            y = (r.height() - iconHeight);
        } else {
            x = (r.width() - (iconWidth * mTotalPage + space * (mTotalPage - 1))) / 2;
            y = (r.height() - iconHeight * 2);
        }

        for (int i = 0; i < mTotalPage; i++) {

            int resid = R.mipmap.ic_yuan;

            if (i == mCurrentPage) {
                resid = R.mipmap.yuan_dark;
            }

            Rect r1 = new Rect();
            r1.left = x;
            r1.top = y;
            r1.right = x + iconWidth;
            r1.bottom = y + iconHeight;

            Bitmap bmp = BitmapFactory.decodeResource(getResources(), resid);
            canvas.drawBitmap(bmp, null, r1, paint);

            x += iconWidth + space;

        }

    }

}