package com.example.pcc.doan2_v1.rss;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.pcc.doan2_v1.R;

public class NewsDetailActivity extends AppCompatActivity {

    MyWebView webView;
    ProgressDialog dialog;
    String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail_rss);
        webView=findViewById(R.id.wvData);
        String url=getIntent().getStringExtra("link");
        if(url!=null){
            dialog=new ProgressDialog(this);
            dialog.setMessage("Loading");
            dialog.setCancelable(false);
            dialog.show();
            webView.setWebViewClient(onWebViewLoaded);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//set size image in webview
            // webView.loadUrl("file:///android_asset/demo.html");
            webView.loadUrl(url);
            //get html

        }


    }
    private WebViewClient onWebViewLoaded=new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dialog.dismiss();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            dialog.dismiss();
            Toast.makeText(NewsDetailActivity.this,"error",Toast.LENGTH_SHORT).show();
        }
    };
}
