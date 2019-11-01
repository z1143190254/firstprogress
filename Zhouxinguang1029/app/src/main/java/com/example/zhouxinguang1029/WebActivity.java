package com.example.zhouxinguang1029;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends BaseActivity {


    private WebView webView;

    @Override
    protected void inisData() {
        final String key = getIntent().getStringExtra("key");
        webView.loadUrl(key);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(key);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i("xxx", "加载当前页面");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("xxx", "当前页面加载结束");
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.i("xxx", "加载当前进度为" + newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.i("xxx", "当前标题为" + title);
            }
        });
    }

    @Override
    protected void inisview() {
        webView = findViewById(R.id.web);

    }

    @Override
    protected int inisid() {
        return R.layout.activity_web;
    }
}
