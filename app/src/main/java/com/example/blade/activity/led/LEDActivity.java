package com.example.blade.activity.led;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.blade.R;
import com.example.blade.base.BaseActivity;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class LEDActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    TextInputEditText mContentLed;
    AppCompatTextView mPreviewLed;
    AppCompatSeekBar mRolledSeekerLed;
    AppCompatRadioButton mAdaptiveRadiomanLed;
    RadioGroup mShowtimeRadiogramLed;
    AppCompatSpinner mCompatSpinner;
    TextView mLinesTextView;
    AppCompatSeekBar minesSeeker;
    public int mBgColor;
    public int mFontColor;
    public int mProgress;
    public int mShowStyle;
    public int mMagicStyle;


    @Override
    protected void initData() {
        findViewById(R.id.fontcolor_btn_led).setOnClickListener(v -> showColorPicker(true));
        findViewById(R.id.bgcolor_btn_led).setOnClickListener(v -> showColorPicker(false));
        findViewById(R.id.recommendcolor_btn_led).setOnClickListener(v -> showColorPicker(false));
        findViewById(R.id.reverseColor_led).setOnClickListener(this::onClick);
        findViewById(R.id.start_btn_led).setOnClickListener(v -> startShowLed());
    }

    @Override
    public void initView() {
        mContentLed = findViewById(R.id.content_led);
        mPreviewLed = findViewById(R.id.preview_led);
        mRolledSeekerLed = findViewById(R.id.rollspeed_seekbar_led);
        mAdaptiveRadiomanLed = findViewById(R.id.adaptive_radiobtn_led);
        mShowtimeRadiogramLed = findViewById(R.id.showstyle_radiogroup_led);
        mCompatSpinner = findViewById(R.id.spinner_magicstyle_led);
        mLinesTextView = findViewById(R.id.tv_lines_led);
        minesSeeker = findViewById(R.id.lines_seekbar_led);


        initViewEvent();
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_led;
    }

    private void initViewEvent() {
        mShowtimeRadiogramLed.check(R.id.single_radiobtn_led);
        mShowStyle = R.id.single_radiobtn_led;
        if (!mAdaptiveRadiomanLed.isChecked()) {
            minesSeeker.setEnabled(false);
        }

        mCompatSpinner.setSelection(0);
        mMagicStyle = 0;
        mRolledSeekerLed.setOnSeekBarChangeListener(this);
        mShowtimeRadiogramLed.setOnCheckedChangeListener(this);
        mCompatSpinner.setOnItemSelectedListener(this);
        minesSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mLinesTextView.setText(String.valueOf(progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void startShowLed() {
        Editable mContentLedText = mContentLed.getText();
        if (TextUtils.isEmpty(mContentLedText)) {
            mContentLed.setError("请填写要显示的内容");
            return;
        }
        if (mFontColor == 0) {
            mFontColor = mPreviewLed.getCurrentTextColor();
        }
        if (mBgColor == 0) {
            mBgColor = 0xffffffff;
        }

        Bundle bundle = new Bundle();
        bundle.putString(Const.LED_CONTENT, mContentLedText.toString());
        bundle.putInt(Const.LED_BG_COLOR, mBgColor);
        bundle.putInt(Const.LED_FONT_COLOR, mFontColor);

        switch (mShowStyle) {
            case R.id.single_radiobtn_led:
                bundle.putInt(Const.LED_ROLL_SPEED, mProgress);
                bundle.putBoolean(Const.LED_SINGLE_ISH, true);
                startSingleLED(bundle);
                break;
            case R.id.single_toss_radiobtn_led:
                bundle.putInt(Const.LED_ROLL_SPEED, mProgress);
                bundle.putBoolean(Const.LED_SINGLE_ISH, false);
                startSingleLED(bundle);
                break;
            case R.id.adaptive_radiobtn_led:
                bundle.putString(Const.LED_LINES, mLinesTextView.getText().toString());
                startAdaptiveLED(bundle);
                break;
            case R.id.magic_radiobtn_led:
                bundle.putString(Const.LED_MAGIC_STYLE,
                        getResources().getStringArray(R.array.arrays_led_style)[mMagicStyle]
                );
                startMagicLED(bundle);
                break;
            default:
                break;
        }
    }

    private void startMagicLED(Bundle bundle) {
        if (!mContentLed.getText().toString().contains("#")) {
            mContentLed.setError("至少输入两句话，用'#'分隔");
            return;
        }
        startActivity(new Intent(this, LEDMagicActivity.class).putExtras(bundle));

    }

    private void startAdaptiveLED(Bundle bundle) {
        startActivity(new Intent(this, LEDAutoActivity.class).putExtras(bundle));
    }

    private void startSingleLED(Bundle bundle) {
        startActivity(new Intent(this, LEDSingleActivity.class).putExtras(bundle));

    }

    private void showRecommendColorDialog() {

        final List<LEDRecommendColor> ledRecommendColors = LEDRecommendColorManager.getInstance().getLEDRecommendColors();
        new AlertDialog.Builder(this)
                .setTitle("选择颜色组合")
                .setItems(LEDRecommendColorManager.colorName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        LEDRecommendColor ledRecommendColor = ledRecommendColors.get(which);
                        changePreviewBgColor(ledRecommendColor.getBackgroundColor());
                        changePreviewFontColor(ledRecommendColor.getFontColor());
                    }
                })
                .show();

    }

    private void showColorPicker(final boolean isFontColor) {
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("选择颜色")
                .initialColor(0xffff0000)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setPositiveButton("确定", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        if (isFontColor) {
                            changePreviewFontColor(selectedColor);
                        } else {
                            changePreviewBgColor(selectedColor);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    private void changePreviewBgColor(int selectedColor) {
        mPreviewLed.setBackgroundColor(selectedColor);
        mBgColor = selectedColor;
    }

    private void changePreviewFontColor(int selectedColor) {
        mPreviewLed.setTextColor(selectedColor);
        mFontColor = selectedColor;
    }

    /**
     * 对滚动速度进度条 的监听
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mProgress = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * 对 显示样式 的单选按钮的事件监听
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        mShowStyle = checkedId;
        if (checkedId == R.id.single_radiobtn_led || checkedId == R.id.single_toss_radiobtn_led) {
            if (!mRolledSeekerLed.isEnabled())
                mRolledSeekerLed.setEnabled(true);
        } else {
            if (mRolledSeekerLed.isEnabled())
                mRolledSeekerLed.setEnabled(false);
        }

        if (checkedId != R.id.magic_radiobtn_led) {
            if (mCompatSpinner.isEnabled()) {
                mCompatSpinner.setEnabled(false);
            }
        } else {
            if (!mCompatSpinner.isEnabled()) {
                mCompatSpinner.setEnabled(true);
            }
        }

        if (checkedId == R.id.adaptive_radiobtn_led) {
            if (!minesSeeker.isEnabled()) {
                minesSeeker.setEnabled(true);
            }
        } else {
            if (minesSeeker.isEnabled()) {
                minesSeeker.setEnabled(false);
            }
        }
    }

    /**
     * 对魔法动效 的动效样式的选择的监听
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mMagicStyle = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
//        mMagicStyle = 0;
    }

    private void onClick(View v) {
        if (mBgColor != 0 && mFontColor != 0) {
            int bgColor = mBgColor;
            changePreviewBgColor(mFontColor);
            changePreviewFontColor(bgColor);
        }
    }
}