package cc.xingyan.android.afc.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import cc.xingyan.android.afc.R;

/**
 * Created by YuJiadeng on 2017/3/8.
 */
public class AppView4Part extends FrameLayout {
    private ImageView mIconPartView;
    private TextView mNamePartView;
    private int iconTint;

    public AppView4Part(Context context) {
        super(context);
        init(context, null);
    }

    public AppView4Part(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.widget_app_view_part, this);
        mIconPartView = (ImageView) findViewById(R.id.icon_part);
        mNamePartView = (TextView) findViewById(R.id.name_part);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AppView, 0, 0);
        final int iconResId = a.getResourceId(R.styleable.AppView_appIcon, 0);
        final CharSequence name = a.getText(R.styleable.AppView_appName);
        final int iconTint = a.getColor(R.styleable.AppView_appIconTint, 0);
        a.recycle();

        setIconResource(iconResId);
        setName(name);
        setIconTint(iconTint);
    }

    public void setName(CharSequence text) {
        mNamePartView.setText(text);
    }

    public void setIconResource(int iconResId) {
//        ContextCom 代替TintManager获取drawable 2020.11.24
//        Drawable icon = TintManager.get(getContext()).getDrawable(iconResId);
        Drawable icon = ContextCompat.getDrawable(getContext(),iconResId);
        DrawableCompat.setTint(icon, this.iconTint);
        mIconPartView.setImageDrawable(icon);
    }

    public void setIconTint(int iconTint) {
        this.iconTint = iconTint;
        if (mIconPartView.getDrawable() != null) {
            DrawableCompat.setTint(mIconPartView.getDrawable(), iconTint);
            mIconPartView.invalidateDrawable(mIconPartView.getDrawable());
        }
    }

    public void setNameColor(int color){
        mNamePartView.setTextColor(color);
    }
}
