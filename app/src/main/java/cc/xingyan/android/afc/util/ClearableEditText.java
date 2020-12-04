package cc.xingyan.android.afc.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;


import androidx.core.graphics.drawable.DrawableCompat;

import cc.xingyan.android.afc.R;

/**
 * Created by YuJiadeng on 2017/7/25.
 */
public class ClearableEditText extends EditText
        implements EditText.OnFocusChangeListener {

    public static final String TAG = "ClearableEditText";

    public ClearableEditText(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearableEditText(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private Drawable mClearDrawable;
    private boolean mIsClearVisible;

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {

        Drawable drawables[] = getCompoundDrawables();
        mClearDrawable = drawables[2];

        final Resources.Theme theme = context.getTheme();

        TypedArray a = theme.obtainStyledAttributes(attrs, R.styleable.ClearableEditText,
                defStyleAttr, defStyleRes);

        int rightDrawableColor = Color.BLACK;

        a.recycle();

        DrawableCompat.setTint(mClearDrawable, rightDrawableColor);

        setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtil.debug(TAG, "onTextChanged " + s);

                setClearDrawableVisible(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setClearDrawableVisible(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (getError() == null && mIsClearVisible && event.getAction() == MotionEvent.ACTION_UP) {

            float x = event.getX();
            if (x >= getWidth() - getTotalPaddingRight() && x <= getWidth() - getPaddingRight()) {
                Log.d(TAG, "点击清除按钮！");

                clearText();

            }
        }

        return super.onTouchEvent(event);
    }

    private void clearText() {
        if (getText().length() > 0) {
            setText("");
        }
    }


    public void setClearDrawableVisible(boolean isVisible) {

        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                isVisible ? mClearDrawable : null, getCompoundDrawables()[3]);

        mIsClearVisible = isVisible;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.d(TAG, "getTotalPaddingTop = " + getTotalPaddingTop());
        Log.d(TAG, "getExtendedPaddingTop = " + getExtendedPaddingTop());

        if (getError() == null) {
            if (hasFocus) {
                if (getText().length() > 0) {
                    setClearDrawableVisible(true);
                }
            } else {
                setClearDrawableVisible(false);
            }
        }
    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        if (error != null) {
            setClearDrawableVisible(true);
        }
        super.setError(error, icon);
    }
}
