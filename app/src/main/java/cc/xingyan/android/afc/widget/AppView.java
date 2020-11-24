/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;


import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.TintContextWrapper;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.TintAwareDrawable;

import cc.xingyan.android.afc.R;

/**
 * Created by San on 10/8/15.
 */
public class AppView extends FrameLayout {

    private ImageView mIconView;
    private TextView mNameView;
    private TextView mBadgeView;
    private int iconTint;

    public AppView(Context context) {
        super(context);
        init(context, null);
    }

    public AppView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.widget_app_view, this);
        mIconView = (ImageView) findViewById(R.id.icon);
        mNameView = (TextView) findViewById(R.id.name);
        mBadgeView = (TextView) findViewById(R.id.badge);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AppView, 0, 0);
        final int iconResId = a.getResourceId(R.styleable.AppView_appIcon, 0);
        final CharSequence name = a.getText(R.styleable.AppView_appName);
        final int iconTint = a.getColor(R.styleable.AppView_appIconTint, 0);
        a.recycle();

        setIconResource(iconResId);
        setName(name);
        setBadgeNumber(0);
        setIconTint(iconTint);
    }

    public void setName(CharSequence text) {
        mNameView.setText(text);
    }

    public void setIconResource(int iconResId) {
//        ContextCom 代替TintManager获取drawable 2020.11.24
//        Drawable icon = TintManager.get(getContext()).getDrawable(iconResId);
        Drawable icon = ContextCompat.getDrawable(getContext(),iconResId);
        DrawableCompat.setTint(icon, this.iconTint);
        mIconView.setImageDrawable(icon);
    }

    public void setBadgeNumber(int number) {
        if (number <= 0) {
            mBadgeView.setVisibility(View.INVISIBLE);
        } else {
            mBadgeView.setVisibility(View.VISIBLE);
            mBadgeView.setText(Integer.toString(number));
        }
    }

    public void setIconTint(int iconTint) {
        this.iconTint = iconTint;
        if (mIconView.getDrawable() != null) {
            DrawableCompat.setTint(mIconView.getDrawable(), iconTint);
            mIconView.invalidateDrawable(mIconView.getDrawable());
        }
    }
}
