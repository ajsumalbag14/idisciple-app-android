package com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment.resourcestab;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ph.idisciple.idiscipleapp.R;
import com.ph.idisciple.idiscipleapp.ui.BaseActivity;

import butterknife.OnClick;

public class ViewResourceActivity extends BaseActivity {

    @OnClick(R.id.clBackOption)
    public void onBackButtonClick(){
        onBackPressed();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_view_resource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView mWebView = (WebView) findViewById(R.id.webView);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String type = bundle.getString("type", "file");
            switch (type){
                case "file":
                    mWebView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + bundle.getString("url", ""));
                    break;
                case "video":
                    mWebView.loadUrl(bundle.getString("url", ""));
                    break;
            }

        }
    }
}
