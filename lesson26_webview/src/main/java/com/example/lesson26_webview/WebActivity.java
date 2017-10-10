package com.example.lesson26_webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by 怪蜀黍 on 2016/12/19.
 */

public class WebActivity extends AppCompatActivity  implements View.OnClickListener{
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.web_view);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        webView.setWebViewClient(webViewClient);//让其在当前程序加载网页
        webView.setWebChromeClient(webChromeClient);//怎样知道网页加载了多少
        WebSettings settings= webView.getSettings();//浏览器内容的设置
        settings.setDefaultTextEncodingName("utf-8");//设置文本解析时默认编码名称
        settings.setJavaScriptEnabled(true);//设置javaScript可用

        webView.addJavascriptInterface(new SMSSend(),"sms");//添加javaScript与android的交互接口


        Intent in = getIntent();
        int action = in.getIntExtra("action", 0);
        String data = in.getStringExtra("data");
        switch (action) {
            case 0:
                if (TextUtils.isEmpty(data)) {
                    data = "http://192.168.56.1:8080/";
//                    data="http://192.168.43.218:8080/";
                }
//                加载指定的url
//                webView.loadUrl(data);
//                webView.loadUrl("file:///android_asset/Index01.html");
                webView.loadUrl("file:///android_asset/index.html");
                break;
            case 1:
//                添加本地页面
                /************************************************************/
//                try {
//                    InputStream is = getAssets().open("Index01.html");
//                    BufferedReader reader=new BufferedReader(new InputStreamReader(is));
//                    StringBuffer sb=new StringBuffer();
//                    while(reader.ready()){
//                        sb.append(reader.readLine());
//                    }
//                    reader.close();
//                    is.close();
////                    webView.loadData(sb.toString(),"text/html",null);
////                    参数baseUrl，设置页面中相对地址的基准地址
//                    webView.loadDataWithBaseURL("file:///android_asset/",sb.toString(),"text/html","utf-8",null);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                webView.loadData("<a href='http://192.168.56.1:8080/'>tomcat</a>", "text/html", null);
                /**************************以上直接取消注释可以直接实现**********************************/
                webView.loadData("<a href='#'>张三的信息</a>","text/html;charset=utf-8","utf-8");
                webView.loadDataWithBaseURL(null,"<a href='#'>张三的信息</a>",null,"utf-8",null);
                break;
        }
    }

    private WebViewClient webViewClient = new WebViewClient() {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            view.loadUrl(String.valueOf(request.getUrl()));
//            return super.shouldOverrideUrlLoading(view, request);
//        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;//处理完成
        }
    };
    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {

                progressBar.setVisibility(WebView.GONE);
            } else if (newProgress >= 0) {
                progressBar.setVisibility(WebView.VISIBLE);
            }
            progressBar.setProgress(newProgress);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(WebActivity.this, message, Toast.LENGTH_SHORT).show();
//            必须调用，否则网页将一直停滞在alert这个函数这里
            result.confirm();
            return true;
        }
    };

    @Override
    public void onClick(View v) {
        webView.loadUrl("javascript:show('======来自Android的调用======')");
    }

    class SMSSend{
//        在4.0以上的api时，许哟添加注解使用
        @JavascriptInterface
        public void send(String address,String msg){
//            获取短信管理者
            SmsManager manager=SmsManager.getDefault();
            Log.e("aaaa","========aaaa=======manager"+manager.toString());
//            发送文本短信1：接收地址，2：短信中心号码3：要发送的内容，4：发送成功的通知，5，接收成功的通知
            manager.sendTextMessage(address,null,msg,null,null);
        }
    }
}
