package returnt.semplnews;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserActivity extends AppCompatActivity {

    /**
     * inicialize
     */
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * load hed title WebView
         */
        getSupportActionBar().setTitle(Html.fromHtml(getIntent().getStringExtra("tile").toString()));
        setContentView(R.layout.activity_brows);

        mWebView = (WebView) findViewById(R.id.webView1);
        mWebView.setWebViewClient(new HelloWebViewClient());

        /**
         * add js
         */
        mWebView.getSettings().setJavaScriptEnabled(true);

        /**
         * load url
         */
        Log.d("wwww", getIntent().getStringExtra("url"));
        mWebView.loadUrl(getIntent().getStringExtra("url"));
    }

    /**
     * reassign back click device
     */
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

/**
 * intercept linc open device
 */
class HelloWebViewClient extends WebViewClient
{
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        view.loadUrl(url);
        return true;
    }
}
