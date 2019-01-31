package net.winnerawan.madiun.ui.tv;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.winnerawan.madiun.R;
import net.winnerawan.madiun.di.component.ActivityComponent;
import net.winnerawan.madiun.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class TvFragment extends BaseFragment implements TvView {

    @Inject
    TvMvpPresenter<TvView> presenter;

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

    }
}
