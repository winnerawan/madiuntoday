package net.winnerawan.madiun.ui.about;

import android.os.Bundle;
import android.view.View;

import net.winnerawan.madiun.ui.base.BaseFragment;

import javax.inject.Inject;

public class AboutFragment extends BaseFragment implements AboutView {

    @Inject
    AboutMvpPresenter<AboutView> presenter;

    public static AboutFragment newInstance() {

        Bundle args = new Bundle();

        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUp(View view) {

    }
}
