package net.winnerawan.madiun.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.base.BaseActivity;
import com.google.android.gms.ads.*;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity implements DetailView {

    @Inject
    DetailMvpPresenter<DetailView> presenter;


    @Inject
    AdRequest adRequest;
    private InterstitialAd mInterstitialAd;

    @Inject
    AdView adView;
    @BindView(R.id.placeholder)
    LinearLayout placeholder;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.post_content_tv)
    HtmlTextView postContentTv;
    @BindView(R.id.action_layout)
    LinearLayout actionLayout;
    @BindView(R.id.post_author_name_tv)
    TextView postAuthorNameTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }



    @Override
    protected void setUp() {
//        mInterstitialAd = new InterstitialAd(getApplicationContext());
//        mInterstitialAd.setAdUnitId(presenter.getIntersId());
//
//        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void showPost() {
        placeholder.setVisibility(View.GONE);
        webview.setVisibility(View.VISIBLE);
        postContentTv.setVisibility(View.VISIBLE);
        postAuthorNameTv.setVisibility(View.GONE);
        actionLayout.setVisibility(View.VISIBLE);
        
        //if (post.)
    }
}
