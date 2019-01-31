package net.winnerawan.madiun.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.ui.about.AboutFragment;
import net.winnerawan.madiun.ui.adapter.PostAdapter;
import net.winnerawan.madiun.ui.base.BaseActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import net.winnerawan.madiun.ui.dbhcht.DBHCHTFragment;
import net.winnerawan.madiun.ui.gallery.GalleryFragment;
import net.winnerawan.madiun.ui.news.NewsFragment;
import net.winnerawan.madiun.ui.tv.TvFragment;
import net.winnerawan.madiun.utils.ViewUtils;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainView, BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainMvpPresenter<MainView> presenter;

    @Inject
    AdRequest adRequest;

    private InterstitialAd mInterstitialAd;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(net.winnerawan.madiun.R.layout.activity_main);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();

    }


    @Override
    protected void setUp() {
        fragmentManager =  getSupportFragmentManager();
        openFragment(NewsFragment.newInstance());
        mNavigation.setOnNavigationItemSelectedListener(this);
        ViewUtils.setStatusBar(this);
        setUpToolbar();
//        mInterstitialAd = new InterstitialAd(getApplicationContext());
//        mInterstitialAd.setAdUnitId(presenter.getIntersId());
//        mInterstitialAd.loadAd(adRequest);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_news :
                setToolbarElevation(0F);
                fragment = NewsFragment.newInstance();
                break;
            case R.id.action_tv :
                fragment = TvFragment.newInstance();
                setToolbarElevation(0F);
                break;
            case R.id.action_gallery :
                fragment = GalleryFragment.newInstance();
                setToolbarElevation(3F);
                break;
            case R.id.action_dbhcht :
                fragment = DBHCHTFragment.newInstance();
                setToolbarElevation(3F);
                break;
            case R.id.action_about :
                fragment = AboutFragment.newInstance();
                setToolbarElevation(0F);
                break;
        }
        openFragment(fragment);
        return true;
    }

    private void openFragment(Fragment fragment) {
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
    }

    public void setToolbarElevation(float elevation) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            mToolbar.setElevation(elevation);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
