package com.cdwx.moka.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdwx.moka.R;

/**
 * Created by KeKe on 2016/11/8.
 * 通用顶部，默认点击返回是finish
 */
public class TopBar extends RelativeLayout {
    private boolean mShowRightView;
    private boolean mShowLeftView;
    private boolean mShowTitle;
    private ImageView mIvBackView;
    private TextView mTvTitle, mTvRightView;
    private Context mContext;
    private String mTitleText;
    private String mRightViewText;
    private float mTitleSize;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.top_bar, this, true);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
            mShowRightView = ta.getBoolean(R.styleable.TopBar_showRightView, false);
            mShowLeftView = ta.getBoolean(R.styleable.TopBar_showLeftView, true);
            mShowTitle = ta.getBoolean(R.styleable.TopBar_showTitle, true);
            mTitleText = ta.getString(R.styleable.TopBar_titleText);
            mRightViewText = ta.getString(R.styleable.TopBar_right_view_text);
            mTitleSize = ta.getDimension(R.styleable.TopBar_titleSize, 14);
            ta.recycle();
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mIvBackView = (ImageView) findViewById(R.id.iv_back);
        mTvRightView = (TextView) findViewById(R.id.tv_right);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        showLeftView(mShowLeftView);
        showRightView(mShowRightView);
        showTitle(mShowTitle);
        setTitleText(mTitleText);
        setRightViewText(mRightViewText);
        setTitleSize(mTitleSize);
        mIvBackView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) mContext).finish();
            }
        });
    }

    public void setTitleSize(float size) {
        if (size < 0) {
            return;
        }
        mTvTitle.setTextSize(size);
    }

    public void setRightViewText(String text) {
        if (text == null) {
            return;
        }
        mTvRightView.setText(text);
    }

    public void showRightView(boolean isShow) {
        if (isShow) {
            mTvRightView.setVisibility(VISIBLE);
        } else {
            mTvRightView.setVisibility(GONE);
        }
    }

    public void setTitleText(String titleText) {
        if (titleText == null) {
            return;
        }
        mTvTitle.setText(titleText);
    }

    public void showLeftView(boolean isShow) {
        if (isShow) {
            mIvBackView.setVisibility(VISIBLE);
        } else {
            mIvBackView.setVisibility(GONE);
        }
    }

    public void showTitle(boolean isShow) {
        if (isShow) {
            mTvTitle.setVisibility(VISIBLE);
        } else {
            mTvTitle.setVisibility(GONE);
        }
    }

    public void setOnBackClickListener(OnClickListener clickListener) {
        if (mIvBackView != null) {
            mIvBackView.setOnClickListener(clickListener);
        }
    }

    public void setOnRightViewClickListener(OnClickListener clickListener) {
        if (mTvRightView != null) {
            mTvRightView.setOnClickListener(clickListener);
        }
    }
}
