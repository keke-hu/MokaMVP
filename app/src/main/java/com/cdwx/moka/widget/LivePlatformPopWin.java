package com.cdwx.moka.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdwx.moka.R;

/**
 * Created by KeKe on 2016/9/14.
 */
public class LivePlatformPopWin extends PopupWindow {
    TagCloudLayout tclLivePlatform;
    TextView tvCancel, tvConfirm;
    View parent;
    OperateListener operateListener;
    private Display display;

    public LivePlatformPopWin(Context context) {
        this(context, null, 0);
    }

    public LivePlatformPopWin(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LivePlatformPopWin(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        this.parent = LayoutInflater.from(context).inflate(R.layout.live_platform_layout, null);
        tvCancel = (TextView) parent.findViewById(R.id.tv_cancel);
        tvConfirm = (TextView) parent.findViewById(R.id.tv_confirm);
        tclLivePlatform = (TagCloudLayout) parent.findViewById(R.id.tcl_live_platform);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operateListener != null) {
                    operateListener.cancel();
                }
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operateListener != null) {
                    operateListener.confirm();
                }
            }
        });
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.parent.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        // 设置视图
        this.setContentView(this.parent);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth((int) (display
                .getWidth() * 0.9));
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);
    }

    public void setOperateListener(OperateListener operateListener) {
        this.operateListener = operateListener;
    }

    public void setTClAdapter(BaseAdapter adapter) {
        tclLivePlatform.setAdapter(adapter);
    }

    public void setItemClickListener(TagCloudLayout.TagItemClickListener itemClickListen) {
        tclLivePlatform.setItemClickListener(itemClickListen);
    }

    public interface OperateListener {
        void cancel();

        void confirm();
    }
}
