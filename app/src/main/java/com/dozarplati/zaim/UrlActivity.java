package com.dozarplati.zaim;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.dozarplati.zaim.utils.AFURLS;
import com.dozarplati.zaim.utils.SnackBarMessage;


public class UrlActivity extends AppCompatActivity {
    private Object wv;
    private static final int FILE_CHOOSER_RESULT_CODE = 10000;
    private final String KEY = "key";

    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().hide();
        //getSupportActionBar().hide();
        //getSupportActionBar().setTitle(getIntent().getExtras().getString("title"));
        ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(
                R.layout.toolbar,
                null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        TextView text = actionBarLayout.findViewById(R.id.title);
        text.setText(getIntent().getExtras().getString("title"));
        actionBar.setCustomView(actionBarLayout);
        showWebView(getIntent().getAction());
    }

    public void showWebView(String url) {

        setRequestedOrientation(4);
        Window window = getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(-16777216);
        }
        this.wv = new WebView(this);
        ((WebView) this.wv).getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies((WebView) this.wv, true);
        }
        else{
            CookieManager.getInstance().setAcceptCookie(true);}
        //((WebView) this.wv).setWebChromeClient(new WebChromeClient());
        ((WebView) this.wv).setWebViewClient(new MyWebViewClient());
        //((WebView) this.wv).setWebViewClient(new WebViewClient());
        ((WebView) this.wv).setWebChromeClient(new MyWebChromeClient());
        ((WebView) this.wv).getSettings().setJavaScriptEnabled(true);
        ((WebView) this.wv).getSettings().setAllowFileAccess(true);
        ((WebView) this.wv).getSettings().setLoadWithOverviewMode(true);
        ((WebView) this.wv).getSettings().setUseWideViewPort(true);
        ((WebView) this.wv).getSettings().setDomStorageEnabled(true);
        //((WebView) this.wv).getSettings().setBuiltInZoomControls(true);
        ((WebView) this.wv).getSettings().setPluginState(WebSettings.PluginState.ON);
        //((WebView) this.wv).getSettings().setSupportZoom(true);
        String s = url+ AFURLS.getInstance().getLink();
        ((WebView) this.wv).loadUrl(url);

        setContentView((WebView) this.wv);
        SnackBarMessage.getMessage((WebView) this.wv, "Загрузка страницы");

    }

    public void onBackPressed() {
        if (((WebView) this.wv).canGoBack()) {
            ((WebView) this.wv).goBack();
            // ((APP)getApplication()).add();
        }
        else
            finish();
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {

            Toast.makeText(getApplicationContext(), "Oh no! " + description,
                    Toast.LENGTH_SHORT).show();

        }



        public void onPageFinished(WebView view, String url) {
            String pageTitle = view.getTitle();
            if(pageTitle.equals("404")){
                //finish();
               // startActivity(new Intent(getApplication(), Menu.class));
            }
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        private MyWebChromeClient() {
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback) {
            uploadMessage = valueCallback;
            openImageChooserActivity();
        }

        public void openFileChooser(ValueCallback valueCallback, String acceptType) {
            uploadMessage = valueCallback;
            openImageChooserActivity();
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
            uploadMessage = valueCallback;
            openImageChooserActivity();
        }

        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            uploadMessageAboveL = filePathCallback;
            openImageChooserActivity();
            return true;
        }

        private void openImageChooserActivity() {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Choose Image"), FILE_CHOOSER_RESULT_CODE);
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != FILE_CHOOSER_RESULT_CODE) {
            return;
        }
        if (this.uploadMessage != null || this.uploadMessageAboveL != null) {
            Uri result = (data == null || resultCode != -1) ? null : data.getData();
            if (this.uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (this.uploadMessage != null) {
                this.uploadMessage.onReceiveValue(result);
                this.uploadMessage = null;
            }
        }
    }

    @TargetApi(21)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode == FILE_CHOOSER_RESULT_CODE && this.uploadMessageAboveL != null) {
            Uri[] results = null;
            if (resultCode == -1 && intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        results[i] = clipData.getItemAt(i).getUri();
                    }
                }
                if (dataString != null) {
                    results = new Uri[]{Uri.parse(dataString)};
                }
            }
            this.uploadMessageAboveL.onReceiveValue(results);
            this.uploadMessageAboveL = null;
        }
    }

    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        if(Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22)
            return;
        super.applyOverrideConfiguration(overrideConfiguration);
    }
}