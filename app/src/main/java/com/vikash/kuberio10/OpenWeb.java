package com.vikash.kuberio10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class OpenWeb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_web);
        Intent intent = getIntent();
        final String link = intent.getStringExtra("link");

        WebView webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        ProgressBar pgbar = findViewById(R.id.pgbar);
        webView.loadUrl(link);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pgbar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pgbar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
    }
    @Override
    public void onBackPressed() {
        WebView webView=findViewById(R.id.webview);
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }





    }
}