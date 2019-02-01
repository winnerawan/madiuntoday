package net.winnerawan.madiun.ui.detail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.rd.PageIndicatorView;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Article;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Image;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.adapter.HeadLineAdapter;
import net.winnerawan.madiun.ui.base.BaseActivity;
import com.google.android.gms.ads.*;
import net.winnerawan.madiun.ui.gallery.GalleryPagerAdapter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import javax.inject.Inject;
import java.util.List;

public class DetailActivity extends BaseActivity implements DetailView {

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
    private Post post;
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
        mToolbar.setTitle(" ");
        setSupportActionBar(mToolbar);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            post = (Post) bundle.getSerializable("post");
            if (post!=null) {
                setUpPost(post);
            }
        }
//        mInterstitialAd = new InterstitialAd(getApplicationContext());
//        mInterstitialAd.setAdUnitId(presenter.getIntersId());
//
//        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void showArticle(Article article) {
        setUpArticle(article);
    }

    @Override
    public void showGalleries(List<Image> images) {
        pagerAdapter = new GalleryPagerAdapter(images, getApplicationContext());
        viewPager.setAdapter(pagerAdapter);
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
        if (post.getCategories()!=null) {
            Category category = new Category();
            category.setId(post.getCategories().get(0));
            presenter.getRelated(category);
        }
    }
}
