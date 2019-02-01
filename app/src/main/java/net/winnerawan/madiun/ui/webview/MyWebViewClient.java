package net.winnerawan.madiun.ui.webview;

import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MyWebViewClient extends WebViewClient {

    private String sourceUrl;
    private Context context;

    public MyWebViewClient() {
    }

    public MyWebViewClient(Context context){

        this.context = context;
    }

    public MyWebViewClient(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.equals(sourceUrl)) {
            view.loadUrl(url);
        }
        return true;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view,request);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    public WebResourceResponse getWebResourceResponseFromAsset(String assetPath, String mimeType, String encoding) throws IOException {
        InputStream inputStream =  context.getAssets().open(assetPath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int statusCode = 200;
            String reasonPhase = "OK";
            Map<String, String> responseHeaders = new HashMap<String, String>();
            responseHeaders.put("Access-Control-Allow-Origin", "*");
            return new WebResourceResponse(mimeType, encoding, statusCode, reasonPhase, responseHeaders, inputStream);
        }
        return new WebResourceResponse(mimeType, encoding, inputStream);
    }
}
