package net.winnerawan.madiun.ui.tv;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import com.facebook.shimmer.ShimmerFrameLayout;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.YoutubeItem;
import net.winnerawan.madiun.data.network.response.YoutubeResponse;
import net.winnerawan.madiun.di.component.ActivityComponent;
import net.winnerawan.madiun.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

import java.util.List;

public class TvFragment extends BaseFragment implements TvView {

    @Inject
    TvMvpPresenter<TvView> presenter;

    @Inject
    VideosAdapter adapter;

    @BindView(R.id.recycler_videos)
    RecyclerView mRecyclerView;
    @BindView(R.id.shimmer)
    ShimmerFrameLayout mShimmer;

    public static TvFragment newInstance() {

        Bundle args = new Bundle();

        TvFragment fragment = new TvFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }

        return view;
    }


    @Override
    protected void setUp(View view) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        presenter.getVideos();
    }

    @Override
    public void showVideos(List<YoutubeItem> videos) {
        adapter.addItems(videos);
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
}
