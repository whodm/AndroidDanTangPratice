package com.example.whodm.retrofitdemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.whodm.retrofitdemo.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private TextView tv_back;
    private String URL = null;
    private static int CLOSE_DIALOG = 12321;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        URL = i.getStringExtra("Url");
        // 取消标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);
        // 实例化WebView
        webView = (WebView) this.findViewById(R.id.wv);
        webView.loadUrl(URL);
        progressDialog = ProgressDialog.show(WebViewActivity.this,"  ","loading");
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100){
                    handler.sendEmptyMessage(CLOSE_DIALOG);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        tv_back = (TextView) this.findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        /**
//         * 设置WebView的属性，此时可以去执行JavaScript脚本
//         */
//        webView.getSettings().setJavaScriptEnabled(true);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == CLOSE_DIALOG){
                progressDialog.dismiss();
            }
        }
    };

}
