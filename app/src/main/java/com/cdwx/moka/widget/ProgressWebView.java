package com.cdwx.moka.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cdwx.moka.utils.DensityUtil;


/**
 * 带进度条的WebView
 *
 * @author caibin
 */
@SuppressWarnings("deprecation")
public class ProgressWebView extends WebView {

    private ProgressBar progressbar;
    private Handler nHandler;
    private TextView tv_title;
    private Context mContext;

    public void setTitle(TextView tv) {
        tv_title = tv;
    }

    public Handler getnHandler() {
        return nHandler;
    }

    public void setnHandler(Handler nHandler) {
        this.nHandler = nHandler;
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(mContext,1), 0, 0));
        addView(progressbar);
        addJavascriptInterface(new JavaScriptInterface(), "maxer");
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });

    }

    public final class JavaScriptInterface {

        JavaScriptInterface() {
        }

        @JavascriptInterface
        public void finish() {
            ((Activity) mContext).finish();
        }

        @JavascriptInterface
        public void share(String title, String sub, String thumb, String url) {
        }

        @JavascriptInterface
        public void toast(String str) {
            Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void location(String str) {
        }
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
                if (nHandler != null) {
                    nHandler.sendEmptyMessage(10);
                }
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }

            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (tv_title != null) {
                tv_title.setText(title);
            }
            super.onReceivedTitle(view, title);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}