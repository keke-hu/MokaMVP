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
 * Created by KeKe on 2016/7/17.
 */
public class NumberPickerPouWin extends PopupWindow {

    private CustomNumberPicker mNumberPicker;
    private Context context;
    private Display display;
    private RelativeLayout mRlComplete;

    public NumberPickerPouWin(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public NumberPickerPouWin builder() {
        View view = LayoutInflater.from(context).inflate(R.layout.number_picker_layout, null);
        mNumberPicker = (CustomNumberPicker) view.findViewById(R.id.np_select);
        mRlComplete = (RelativeLayout) view.findViewById(R.id.rl_complete);
        mNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        this.setContentView(view);
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth((int) (display
                .getWidth() * 0.9));
        this.setFocusable(true);
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
        this.setAnimationStyle(R.style.take_photo_anim);
        return this;
    }

    public NumberPickerPouWin setMaxValue(int maxValue) {
        mNumberPicker.setMaxValue(maxValue);
        return this;
    }

    public NumberPickerPouWin setMinValue(int minValue) {
        mNumberPicker.setMinValue(minValue);
        return this;
    }

    public NumberPickerPouWin setValue(int currentValue) {
        mNumberPicker.setValue(currentValue);
        return this;
    }

    public NumberPickerPouWin setOnValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        mNumberPicker.setOnValueChangedListener(valueChangeListener);
        return this;
    }

    public NumberPickerPouWin setCompleteClickListerner(View.OnClickListener onClickListener) {
        mRlComplete.setOnClickListener(onClickListener);
        return this;
    }

}
