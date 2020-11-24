package cc.xingyan.android.afc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import javax.inject.Inject;

import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.inject.Dagger;

/**
 * Created by YuJiadeng on 2016/10/27.
 */
public class Main4ChartForWebActivity extends Activity {
    private WebView webView;
    private Toolbar toolbar;

    private ProgressDialog progressBar;

    @Inject
    Authenticator mAuthenticator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
        setContentView(R.layout.activity_web);
        init();

    }

    private void init(){
        webView = (WebView) findViewById(R.id.web_chart);
        toolbar = (Toolbar)findViewById(R.id.toolbarforchart);

        toolbar.setTitle("运营信息报表");

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        progressBar = ProgressDialog.show(Main4ChartForWebActivity.this, "运营信息报表", "正在加载...");

        SharedPreferences chartPreferences = getSharedPreferences("chart", Activity.MODE_PRIVATE);
        String orgCode = chartPreferences.getString("orgcode", "noinfo");

        String url = "http://aos.mtd-bj.com/mtd/views/ifsmobilereport/ifsReportMobileController/ifsreportlogin.do?userName="
                + mAuthenticator.getAuthenticatedUserId() + "&orgcode=" + orgCode;
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                alertDialog.setTitle("加载失败");
                alertDialog.setMessage(description);
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }

                        });
                alertDialog.show();
            }
        });
    }
}
