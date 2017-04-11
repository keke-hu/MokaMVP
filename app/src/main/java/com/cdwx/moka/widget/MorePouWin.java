package com.cdwx.moka.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdwx.moka.R;

/**
 * Created by KeKe on 2016/8/24.
 * 查看大图更多弹层
 */
public class MorePouWin extends PopupWindow {

    private View view;

    private TextView tv_cancel, tv_save, tv_delete, tvUp;

    public MorePouWin(Context context, View.OnClickListener itemsOnClick) {
        this.view = LayoutInflater.from(context).inflate(R.layout.more_layout, null);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_save = (TextView) view.findViewById(R.id.tv_save);
        tv_delete = (TextView) view.findViewById(R.id.tv_delete);
        tvUp = (TextView) view.findViewById(R.id.tv_up);
        tv_cancel.setOnClickListener(itemsOnClick);
        tv_delete.setOnClickListener(itemsOnClick);
        tv_save.setOnClickListener(itemsOnClick);
        tvUp.setOnClickListener(itemsOnClick);
        init();
    }


    public MorePouWin(Context context, View.OnClickListener itemsOnClick, boolean showTop) {
        this.view = LayoutInflater.from(context).inflate(R.layout.more_layout, null);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_save = (TextView) view.findViewById(R.id.tv_save);
        tv_delete = (TextView) view.findViewById(R.id.tv_delete);
        tv_cancel.setOnClickListener(itemsOnClick);
        tv_delete.setOnClickListener(itemsOnClick);
        tv_save.setOnClickListener(itemsOnClick);
        if (showTop) {
            tvUp = (TextView) view.findViewById(R.id.tv_up);
            tvUp.setOnClickListener(itemsOnClick);
            tvUp.setVisibility(View.VISIBLE);
        }
        init();
    }

    private void init() {
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);
    }


}
