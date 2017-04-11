package com.cdwx.moka.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.cdwx.moka.R;

/**
 * Created by KeKe on 2016/9/8.
 */
public class ToolPouWin extends PopupWindow {

    private View view;
    RecyclerView rv_content;
    private RadioButton rb_select_border, rb_remove_qd, rb_switch_template;
    private Context context;

    public ToolPouWin(Context context) {
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.tool_layout, null);
        rb_remove_qd = (RadioButton) view.findViewById(R.id.rb_remove_qd);
        rb_select_border = (RadioButton) view.findViewById(R.id.rb_select_border);
        rb_switch_template = (RadioButton) view.findViewById(R.id.rb_switch_template);
        rv_content = (RecyclerView) view.findViewById(R.id.rv_content);
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
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.take_photo_anim);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_content.setLayoutManager(linearLayoutManager);
        rv_content.setAdapter(adapter);
    }

    public void setOnClick(View.OnClickListener onClickListener) {
        rb_switch_template.setOnClickListener(onClickListener);
        rb_select_border.setOnClickListener(onClickListener);
        rb_remove_qd.setOnClickListener(onClickListener);
    }
}
