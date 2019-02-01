package net.winnerawan.madiun.ui.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.rd.PageIndicatorView;
import net.winnerawan.madiun.BuildConfig;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Article;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Image;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.adapter.HeadLineAdapter;
import net.winnerawan.madiun.ui.base.BaseActivity;
import com.google.android.gms.ads.*;
import net.winnerawan.madiun.ui.gallery.GalleryPagerAdapter;
import net.winnerawan.madiun.utils.CommonUtils;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import javax.inject.Inject;
import java.util.List;

public class DetailActivity extends BaseActivity implements DetailView, HeadLineAdapter.Callback {

    @Inject
    DetailMvpPresenter<DetailView> presenter;

    @Inject
    HeadLineAdapter adapter;

    private GalleryPagerAdapter pagerAdapter;
    @Inject
    AdRequest adRequest;
    private InterstitialAd mInterstitialAd;

    @Inject
    AdView adView;
    @BindView(R.id.title)
    TextView txtTitle;
    @BindView(R.id.first)
    HtmlTextView txtFirst;
    @BindView(R.id.center)
    HtmlTextView txtCenter;
    @BindView(R.id.related_news)
    RecyclerView mRecyclerView;
    @BindView(R.id.vp_article_image)
    ViewPager viewPager;
    @BindView(R.id.shimmer)
    ShimmerFrameLayout mShimmer;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.image)
    ImageView mImageView;
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView indicator;
    @BindView(R.id.txt_time)
    TextView txtDate;
    @BindView(R.id.containerAds)
    LinearLayout mAds;

    private Post post;
    private boolean isAdsEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        adapter.setCallback(this);
        mToolbar.setTitle(" ");
        setSupportActionBar(mToolbar);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            post = (Post) bundle.getSerializable("post");
            isAdsEnable = bundle.getBoolean("ads_enable");
            if (post!=null) {
                setUpPost(post);
                showBannerAds(isAdsEnable);
            } else {
                //todo from deeplink

            }
        }
    }

    @OnClick(R.id.txt_source)
    void gotoSource() {
        Intent dial = new Intent(Intent.ACTION_VIEW);
        dial.setData(Uri.parse(post.getLink()));
        startActivity(dial);
    }

    @OnClick(R.id.txt_share)
    void share() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, post.getTitle().getRendered());
            String referrerMessage = getString(R.string.app_name);
            referrerMessage = referrerMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            String shareMessage = post.getLink();
            shareMessage = shareMessage +"\n\n\n" + getString(R.string.share_by) +"\n" + getString(R.string.share_by_link);
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            shareIntent.putExtra(Intent.EXTRA_REFERRER, referrerMessage);
            startActivity(Intent.createChooser(shareIntent, getString(R.string.choose)));
        } catch(Exception e) {
            //e.toString();
        }
    }

    @Override
    public void showArticle(Article article) {
        setUpArticle(article);
        Category category = new Category();
        category.setId(post.getCategories().get(0));
        presenter.getRelated(category);
    }

    @Override
    public void showGalleries(List<Image> images) {
        pagerAdapter = new GalleryPagerAdapter(images, getApplicationContext());
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onPostSelected(Post post) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("post", post);
        intent.putExtra("url", post.getLink());
        intent.putExtra("image", post.getJetpackFeaturedMediaUrl());
        startActivity(intent);
        finish();
    }


    @Override
    public void showImage() {
        mImageView.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.GONE);
        indicator.setVisibility(View.GONE);
        Glide.with(this)
                .load(post.getJetpackFeaturedMediaUrl())
                .asBitmap()
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .into(mImageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showRelated(List<Post> posts) {
//        List<Post> related = new ArrayList<>();
//        for (int i=0; i<posts.size(); i++) {
//            posts.remove(0);
//        }
        posts.remove(0);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter.addItems(posts);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mShimmer.startShimmer();
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmer.startShimmer();
    }

    @Override
    public void stopShimmer() {
        mShimmer.stopShimmer();
        mShimmer.setVisibility(View.GONE);
    }

    private void setUpArticle(Article article) {
        txtFirst.setHtml(article.getFirst());
        txtCenter.setHtml(article.getCenter());
    }

    private void setUpPost(Post post) {
        if (post.getTitle().getRendered()!=null) {
            txtTitle.setText(post.getTitle().getRendered());
        }
        if (post.getLink()!=null) {
            presenter.getArticle(post.getLink());
        }
        if(post.getDate()!=null) {
            txtDate.setText(CommonUtils.prettyDateFormat(post.getDate()));
        }
    }

    private void showBannerAds(boolean isAdsEnable) {
        if (isAdsEnable) {
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(presenter.getBanner());
            mAds.addView(adView);
            adView.loadAd(adRequest);
        }
    }
}
