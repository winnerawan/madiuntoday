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
import net.winnerawan.madiun.data.network.App;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Gallery;
import net.winnerawan.madiun.di.component.ActivityComponent;
import net.winnerawan.madiun.ui.base.BaseFragment;
import net.winnerawan.madiun.utils.AppLogger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends BaseFragment implements GalleryView {

    @Inject
    GalleryMvpPresenter<GalleryView> presenter;
    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recycler_news)
    RecyclerView mRecyclerNews;
    @BindView(R.id.shimmer)
    ShimmerFrameLayout mShimmer;
    private Category category;
    private GalleryAdapter adapter;
    private int index = 1;
    private List<Gallery> galleries;

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
        }

        galleries = new ArrayList<>();

        adapter = new GalleryAdapter(getBaseActivity(), galleries);

        adapter.setLoadMoreListener(() -> mRecyclerNews.post(() -> {
            index = index + 1;
            loadMore(index);
        }));

        mRecyclerNews.setHasFixedSize(true);
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        mRecyclerNews.setAdapter(adapter);

        return view;
    }

    @Override
    protected void setUp(View view) {
        category = new Category();
        category.setId(8);
        presenter.getGalleries(category, 1);
    }

    @Override
    public void showGalleries(List<Gallery> galleries) {
        if(galleries.size()>0){
            //add loaded data
            this.galleries.addAll(galleries);
        }else{//result size 0 means there is no more data available at server
            adapter.setMoreDataAvailable(false);
            //telling adapter to stop calling load more as no more server data available
        }
        adapter.notifyDataChanged();
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

    private void loadMore(int index) {
        AppLogger.e("LOAD CAT: "+category.getName() + " & PAGE: "+index);
        presenter.getGalleries(category, index);
    }
}
