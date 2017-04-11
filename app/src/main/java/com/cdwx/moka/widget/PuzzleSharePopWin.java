package com.cdwx.moka.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.cdwx.moka.R;

/**
 * Created by KeKe on 2016/9/26.
 * 分享弹框
 */
public class PuzzleSharePopWin extends PopupWindow {

    public static final int MOMENT = 0;
    public static final int WECHAT = 1;

    private View view;
    private RadioButton rbMoment, rbWeChat;
    private ImageView ivClose;
    private Button btnShare;
    private Context context;
    private ShareListener shareListener;


    public PuzzleSharePopWin(Context context) {
        this(context, null, 0);
    }

    public PuzzleSharePopWin(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PuzzleSharePopWin(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.success_share_layout, null);
        rbMoment = (RadioButton) view.findViewById(R.id.rb_moment);
        rbWeChat = (RadioButton) view.findViewById(R.id.rb_wechat);
        ivClose = (ImageView) view.findViewById(R.id.iv_close);
        btnShare = (Button) view.findViewById(R.id.btn_share);
        this.setOutsideTouchable(true);
        this.view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(shareListener!=null){
                    shareListener.dismiss();
                }
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shareListener != null) {
                    if (rbMoment.isChecked()) {
                        shareListener.share(MOMENT);
                    } else {
                        shareListener.share(WECHAT);
                    }
                }
            }
        });
        this.setContentView(this.view);
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
    }


    public void setShareListener(ShareListener shareListener) {
        this.shareListener = shareListener;
    }


    public interface ShareListener {
        void share(int flag);
        void dismiss();
    }
}
