package net.winnerawan.madiun.ui.gallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.shimmer.ShimmerFrameLayout;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Gallery;
import net.winnerawan.madiun.di.component.ActivityComponent;
import net.winnerawan.madiun.ui.base.BaseFragment;

import javax.inject.Inject;
import java.util.List;

public class GalleryFragment extends BaseFragment implements GalleryView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    GalleryMvpPresenter<GalleryView> presenter;
    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.content_news_srv)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_news)
    RecyclerView mRecyclerNews;
    @BindView(R.id.shimmer)
    ShimmerFrameLayout mShimmer;
    private Category category;
    private GalleryAdapter adapter;

    public static GalleryFragment newInstance() {

        Bundle args = new Bundle();

        GalleryFragment fragment = new GalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
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
        refreshLayout.setOnRefreshListener(this);
        category = new Category();
        category.setId(8);
        presenter.getGalleries(category);
    }

    @Override
    public void showGalleries(List<Gallery> galleries) {
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        adapter = new GalleryAdapter(getBaseActivity(), galleries);
        mRecyclerNews.setAdapter(adapter);
    }

    @Override
    public void setDisableRefreshLayout() {
        if (refreshLayout.isRefreshing()) refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        if (category != null) presenter.getGalleries(category);
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
