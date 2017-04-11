package com.cdwx.moka.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.cdwx.moka.R;

/**
 * Created by KeKe on 2016/9/22.
 */
public class FansNumberPickerPouWin extends PopupWindow {
    private CustomNumberPicker mFansNumberPicker, mUnitNumberPicker;
    private Context context;
    private Display display;
    private RelativeLayout mRlComplete;

    public FansNumberPickerPouWin(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.fans_numberpicker_layout, null);
        mFansNumberPicker = (CustomNumberPicker) view.findViewById(R.id.np_fans_num);
        mUnitNumberPicker = (CustomNumberPicker) view.findViewById(R.id.np_unit);
        mRlComplete = (RelativeLayout) view.findViewById(R.id.rl_complete);
        mFansNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mUnitNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        this.setContentView(view);
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth((int) (display
                .getWidth() * 0.9));
        this.setFocusable(true);
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
        this.setAnimationStyle(R.style.take_photo_anim);
    }


    public FansNumberPickerPouWin setRange(int max, int min) {
        mFansNumberPicker.setMinValue(min);
        mFansNumberPicker.setMaxValue(max);
        return this;
    }


    public FansNumberPickerPouWin setCurrent(int current) {
        mFansNumberPicker.setValue(current);
        return this;
    }


    public FansNumberPickerPouWin setUnit(int current) {
        String[] taskGroupString = {"千", "万"};
        mUnitNumberPicker.setMinValue(0);
        mUnitNumberPicker.setMaxValue(taskGroupString.length - 1);
        mUnitNumberPicker.setDisplayedValues(taskGroupString);
        if (current < 0 || current > 1) {
            mUnitNumberPicker.setValue(0);
        }
        mUnitNumberPicker.setValue(current);
        return this;
    }

    public String getValue() {
        return mFansNumberPicker.getValue() + (mUnitNumberPicker.getValue() % 2 == 0 ? "千" : "万");
    }


    public FansNumberPickerPouWin setCompleteClickListerner(View.OnClickListener onClickListener) {
        mRlComplete.setOnClickListener(onClickListener);
        return this;
    }
}
