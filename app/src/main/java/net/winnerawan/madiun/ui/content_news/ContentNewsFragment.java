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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.shimmer.ShimmerFrameLayout;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.di.component.ActivityComponent;
import net.winnerawan.madiun.ui.adapter.PostAdapter;
import net.winnerawan.madiun.ui.base.BaseFragment;
import net.winnerawan.madiun.ui.detail.DetailActivity;
import net.winnerawan.madiun.ui.webview.WebviewActivity;
import net.winnerawan.madiun.utils.AppConstants;

public class ContentNewsFragment extends BaseFragment implements ContentNewsView, SwipeRefreshLayout.OnRefreshListener, PostAdapter.Callback {

    @Inject
    ContentNewsMvpPresenter<ContentNewsView> presenter;
    @Inject
    PostAdapter adapter;
    @Inject
    LinearLayoutManager mLinearLayoutManager;
    @BindView(R.id.recycler_news)
    RecyclerView mRecyclerNews;
    @BindView(R.id.content_news_srv)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.shimmer)
    ShimmerFrameLayout mShimmer;
    private Category category;

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
        return view;
    }

    @Override
    protected void setUp(View view) {
        adapter.setCallback(this);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        category = (Category) bundle.getSerializable(AppConstants.EXTRAS_DATA_CATEGORY);

        presenter.getNews(category);
    }

    @Override
    public void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void showNews(List<Post> news) {
        mRecyclerNews.setLayoutManager(mLinearLayoutManager);
        adapter.addItems(news);
        mRecyclerNews.setAdapter(adapter);
    }

    @Override
    public void onPostSelected(Post post) {
        Intent intent = new Intent(getBaseActivity(), DetailActivity.class);
        intent.putExtra("post", post);
        intent.putExtra("url", post.getLink());
        startActivity(intent);
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
        if (category != null) presenter.getNews(category);
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
}
