package cc.xingyan.android.afc.widget;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import cc.xingyan.android.afc.R;

/**
 * Created by San on 11/19/15.
 */
public class SyncItemView extends LinearLayout {
    public static final int STATUS_INIT = 0;
    public static final int STATUS_DONE = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_ERROR = 3;

    private ImageView iconView;
    private ImageView loadingView;
    private TextView textView;
    private ProgressBar progressBar;

    public SyncItemView(Context context) {
        super(context);
        init();
    }

    public SyncItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_sync_item, this);
        this.iconView = (ImageView) findViewById(R.id.icon);
        this.loadingView = (ImageView) findViewById(R.id.loading);
        this.textView = (TextView) findViewById(R.id.text);
        this.progressBar = (ProgressBar) findViewById(R.id.progress);
        setLoadingStatus(STATUS_INIT);
    }

    public void setIcon(int resId) {
        iconView.setImageResource(resId);
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setLoadingStatus(int status) {
        switch (status) {
            case STATUS_INIT:
                loadingView.setImageDrawable(null);
                progressBar.setProgress(0);
                break;
            case STATUS_DONE:
                loadingView.setImageResource(R.drawable.ic_cloud_done_black_18dp);
                ObjectAnimator.ofObject(progressBar, "progress", new IntEvaluator(), 0, 100).setDuration(300).start();
                break;
            case STATUS_LOADING:
                loadingView.setImageResource(R.drawable.progress);
                break;
            case STATUS_ERROR:
                loadingView.setImageResource(R.drawable.ic_info_black_24dp);
                break;
        }
    }
}
