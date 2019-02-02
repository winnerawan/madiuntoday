package net.winnerawan.madiun.ui.content_news;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.di.component.ActivityComponent;
import net.winnerawan.madiun.ui.adapter.NewsAdapter;
import net.winnerawan.madiun.ui.base.BaseFragment;
import net.winnerawan.madiun.ui.detail.DetailActivity;
import net.winnerawan.madiun.utils.AppConstants;
import net.winnerawan.madiun.utils.AppLogger;

public class ContentNewsFragment extends BaseFragment implements ContentNewsView, SwipeRefreshLayout.OnRefreshListener, NewsAdapter.Callback {

    @Inject
    ContentNewsMvpPresenter<ContentNewsView> presenter;

    NewsAdapter adapter;
    @Inject
    LinearLayoutManager mLinearLayoutManager;
    @BindView(R.id.recycler_news)
    RecyclerView mRecyclerNews;
    @BindView(R.id.content_news_srv)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.shimmer)
    ShimmerFrameLayout mShimmer;
    private Category category;
    private List<Post> posts = new ArrayList<>();
    private InterstitialAd mInterstitialAd;
    private int index = 1;
    @Inject
    AdRequest adRequest;
    public ContentNewsFragment() {
        // Required empty public constructor
    }

    public static ContentNewsFragment newInstance(Category category) {
        Bundle args = new Bundle();
        args.putSerializable(AppConstants.EXTRAS_DATA_CATEGORY, category);
        ContentNewsFragment fragment = new ContentNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_news, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
            refreshLayout.setOnRefreshListener(this);
            refreshLayout.setColorSchemeResources(R.color.colorAccent);
        }

        adapter = new NewsAdapter(getBaseActivity(), posts);

        adapter.setLoadMoreListener(() -> mRecyclerNews.post(() -> {
            index = index + 1;
            AppLogger.e("LOAD PAGE: "+index);
            loadMore(index);
        }));

        mRecyclerNews.setHasFixedSize(true);
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        mRecyclerNews.setAdapter(adapter);

        return view;
    }

    @Override
    protected void setUp(View view) {
        adapter.setCallback(this);
        mInterstitialAd = new InterstitialAd(getBaseActivity());
        mInterstitialAd.setAdUnitId(presenter.getInters());
        mInterstitialAd.loadAd(adRequest);

        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        category = (Category) bundle.getSerializable(AppConstants.EXTRAS_DATA_CATEGORY);

        presenter.getNews(category, 1);
    }

    @Override
    public void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void showNews(List<Post> news) {
        if(news.size()>0){
            //add loaded data
            posts.addAll(news);
        }else{//result size 0 means there is no more data available at server
            adapter.setMoreDataAvailable(false);
            //telling adapter to stop calling load more as no more server data available
        }
        adapter.notifyDataChanged();
    }

    @Override
    public void onPostSelected(Post post) {
        Intent intent = new Intent(getBaseActivity(), DetailActivity.class);
        intent.putExtra("post", post);
        intent.putExtra("url", post.getLink());
        intent.putExtra("image", post.getJetpackFeaturedMediaUrl());
        intent.putExtra("ads_enable", false);
        startActivity(intent);
    }

    @Override
    public void onPostSelectedWithAds(Post post) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    mInterstitialAd.loadAd(adRequest);
                    Intent intent = new Intent(getBaseActivity(), DetailActivity.class);
                    intent.putExtra("post", post);
                    intent.putExtra("url", post.getLink());
                    intent.putExtra("image", post.getJetpackFeaturedMediaUrl());
                    intent.putExtra("ads_enable", true);
                    startActivity(intent);
                    super.onAdClosed();
                }
            });
        } else {
            Intent intent = new Intent(getBaseActivity(), DetailActivity.class);
            intent.putExtra("post", post);
            intent.putExtra("url", post.getLink());
            intent.putExtra("image", post.getJetpackFeaturedMediaUrl());
            startActivity(intent);
        }
    }

    @Override
    public void setDisableRefreshLayout() {
        if (refreshLayout.isRefreshing()) refreshLayout.setRefreshing(false);
    }

    @Override
    public void setEnableRefreshLayout() {
        if (!refreshLayout.isRefreshing()) refreshLayout.setRefreshing(true);
    }

    @Override
    public void onRefresh() {
        if (category != null) presenter.getNews(category, 1);
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
        if (mShimmer!=null) {
            mShimmer.stopShimmer();
            mShimmer.setVisibility(View.GONE);
        }
    }

    private void loadMore(int page) {
        presenter.getNews(category, page);
    }
}
