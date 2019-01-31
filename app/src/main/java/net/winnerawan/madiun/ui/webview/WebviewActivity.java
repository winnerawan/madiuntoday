package net.winnerawan.madiun.ui.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.ui.base.BaseActivity;

public class WebviewActivity extends BaseActivity implements WebviewView {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    WebviewMvpPresenter<WebviewView> presenter;

    private boolean isWebviewLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(this);
        setUp();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        webview.onPause();
        webview.removeAllViews();
        webview.destroyDrawingCache();
        webview = null;
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        presenter.onDetach();
        super.onStop();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void setUp() {
        String url = getIntent().getStringExtra("url");
        if (url == null || url.isEmpty()) {
            finish();
            return;
        }
        progressBar.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                /*if (progress == 100) {
                    isWebviewLoaded = true;
                    progressBar.setVisibility(View.GONE);
                } else {
                    isWebviewLoaded = false;
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(progress);
                }*/
            }
        });
        webview.setWebViewClient(new MyWebViewClient());
        webview.loadUrl(url);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
