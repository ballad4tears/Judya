package com.judya.judya;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class InsertData_MainActivity extends AppCompatActivity {

    private WebView webviewpayment;
    private ProgressBar progress;
    private String Url_beat="";



    boolean check_login=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lotto_webview);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setMax(100);
        // https://www.lotto.ktbnetbank.com/KTBLotto/#/login
        Url_beat="http://projectandroid.top/Emergency/index.php";
        //https://www.lotto.ktbnetbank.com/KTBLotto/#/relogin




        progress.setVisibility(View.GONE);
        webviewpayment = (WebView) findViewById(R.id.webView1);
        webviewpayment.setWebChromeClient(new MyWebViewClient());
        webviewpayment.getSettings().setJavaScriptEnabled(true);
        // webviewpayment.loadUrl("http://www.lotto.ktbnetbank.com/KTBLotto/#/login");


        //url.equals("https://www.lotto.ktbnetbank.com/"  เข้าไม่ได้

        webviewpayment.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {

            }
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.w("RETURN",url + "  >>>>>>>>> RETURN");




                super.onPageFinished(view, url);
            }
        });
        //http://www.lotto.ktbnetbank.com/KTBLotto/#/login
        webviewpayment.loadUrl(Url_beat);

    }

    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            InsertData_MainActivity.this.setValue(newProgress);
            if (newProgress >= 100) {
                progress.setVisibility(View.GONE);
            } else {
                progress.setVisibility(View.VISIBLE);

            }
            super.onProgressChanged(view, newProgress);

        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {


            Log.w("RETURN",message + "  >>>>>>>>> RETURN");
            Log.d("RETURN",message + "  >>>>>>>>> RETURN");
            result.confirm();

            return true;
        }
    }
    public void setValue(int progress) {
        this.progress.setProgress(progress);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
