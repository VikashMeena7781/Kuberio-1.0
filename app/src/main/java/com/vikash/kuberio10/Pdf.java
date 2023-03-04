package com.vikash.kuberio10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class Pdf extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        mWebView = findViewById(R.id.webview);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
//        String pdfUrl = "https://drive.google.com/uc?id=0B2c5H7AbqQh7QjJwSVhrWFViS2wzYzUtdDNMbXo3cnFHeE0w&export=download";
        mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + link);






    }
}