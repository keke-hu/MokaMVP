package com.cdwx.moka.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.cdwx.moka.R;

/**
 * Created by KeKe on 2016/12/7.
 * 通告分享
 */

public class NoticeSharePouWin extends PopupWindow {

    public NoticeSharePouWin(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        initView(context, itemsOnClick);
    }

    private void initView(final Activity context, View.OnClickListener itemsOnClick) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_share_layout, null);
        RelativeLayout rl_moment = (RelativeLayout) view.findViewById(R.id.rl_moment);
        rl_moment.setOnClickListener(itemsOnClick);
        RelativeLayout rl_wechat = (RelativeLayout) view.findViewById(R.id.rl_wechat);
        rl_wechat.setOnClickListener(itemsOnClick);
        RelativeLayout rl_qq = (RelativeLayout) view.findViewById(R.id.rl_qq);
        rl_qq.setOnClickListener(itemsOnClick);
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        // 设置视图
        this.setContentView(view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        this.setAnimationStyle(R.style.take_photo_anim);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(context, 1f);
            }
        });
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha(context, 0.5f);
    }

    /**
     * 设置添加屏幕的背景透明度
     */
    private void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}

