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
 * 三围选择器
 */
public class MeasurementsNumberPickerPouWin extends PopupWindow {
    private CustomNumberPicker mBustNumberPicker, mWaistNumberPicker, mHipsNumberPicker;
    private Context context;
    private Display display;
    private RelativeLayout mRlComplete;

    public MeasurementsNumberPickerPouWin(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public MeasurementsNumberPickerPouWin builder() {
        View view = LayoutInflater.from(context).inflate(R.layout.measurements_layout, null);
        mBustNumberPicker = (CustomNumberPicker) view.findViewById(R.id.np_bust);
        mWaistNumberPicker = (CustomNumberPicker) view.findViewById(R.id.np_waist);
        mHipsNumberPicker = (CustomNumberPicker) view.findViewById(R.id.np_hips);
        mRlComplete = (RelativeLayout) view.findViewById(R.id.rl_complete);
        mBustNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mWaistNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mHipsNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        this.setContentView(view);
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth((int) (display
                .getWidth() * 0.9));
        this.setFocusable(true);
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
        this.setAnimationStyle(R.style.take_photo_anim);
        return this;
    }


    public MeasurementsNumberPickerPouWin setMaxValue(int maxValue) {
        mBustNumberPicker.setMaxValue(maxValue);
        mWaistNumberPicker.setMaxValue(maxValue);
        mHipsNumberPicker.setMaxValue(maxValue);
        return this;
    }

    public MeasurementsNumberPickerPouWin setMinValue(int minValue) {
        mBustNumberPicker.setMinValue(minValue);
        mWaistNumberPicker.setMinValue(minValue);
        mHipsNumberPicker.setMinValue(minValue);
        return this;
    }

    public MeasurementsNumberPickerPouWin setBustNumberPickerValue(int currentValue) {
        mBustNumberPicker.setValue(currentValue);
        return this;
    }

    public MeasurementsNumberPickerPouWin setWaistNumberPickerValue(int currentValue) {
        mWaistNumberPicker.setValue(currentValue);
        return this;
    }

    public MeasurementsNumberPickerPouWin setHipsNumberPickerValue(int currentValue) {
        mHipsNumberPicker.setValue(currentValue);
        return this;
    }


    public MeasurementsNumberPickerPouWin setOnBustValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        mBustNumberPicker.setOnValueChangedListener(valueChangeListener);
        return this;
    }

    public MeasurementsNumberPickerPouWin setOnWaistValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        mWaistNumberPicker.setOnValueChangedListener(valueChangeListener);
        return this;
    }

    public MeasurementsNumberPickerPouWin setOnHipsValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        mHipsNumberPicker.setOnValueChangedListener(valueChangeListener);
        return this;
    }

    public MeasurementsNumberPickerPouWin setCompleteClickListerner(View.OnClickListener onClickListener) {
        dismiss();
        mRlComplete.setOnClickListener(onClickListener);
        return this;
    }

}
