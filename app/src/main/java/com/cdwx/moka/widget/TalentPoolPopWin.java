package com.cdwx.moka.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.cdwx.moka.R;

/**
 * Created by KeKe on 2017/2/22.
 * 人才库弹框
 */

public class TalentPoolPopWin extends PopupWindow {


    private View view;
    private ImageView ivClose;
    private Button btnAgree;
    private Context context;
    private TalentPoolListener talentPoolListener;


    public TalentPoolPopWin(Context context) {
        this(context, null, 0);
    }

    public TalentPoolPopWin(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TalentPoolPopWin(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.layout_talent_pool, null);
        btnAgree = (Button) view.findViewById(R.id.btn_agree);
        ivClose = (ImageView) view.findViewById(R.id.iv_close);
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
            }
        });
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (talentPoolListener != null) {
                    talentPoolListener.agree();
                }
            }
        });
        this.setContentView(this.view);
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setClippingEnabled(false);
    }


    public void setTalentPoolListener(TalentPoolListener talentPoolListener) {
        this.talentPoolListener = talentPoolListener;
    }


    public interface TalentPoolListener {
        void agree();
    }
}
