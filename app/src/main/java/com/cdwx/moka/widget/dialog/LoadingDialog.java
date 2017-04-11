package com.cdwx.moka.widget.dialog;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.cdwx.moka.R;


/**
 * Created by KeKe on 2017/3/17.
 * 加载对话框
 */

public class LoadingDialog extends Dialog {
    Context context;

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.layout_loading);
        ImageView imageView = (ImageView) findViewById(R.id.iv_circle);
        Animator animator = AnimatorInflater.loadAnimator(context, R.animator.anim_loading_circle);
        animator.setTarget(imageView);
        animator.start();
    }

}
