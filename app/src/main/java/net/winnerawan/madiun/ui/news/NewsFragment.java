package net.winnerawan.madiun.ui.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.di.component.ActivityComponent;
import net.winnerawan.madiun.ui.adapter.HeadLineAdapter;
import net.winnerawan.madiun.ui.adapter.PostAdapter;
import net.winnerawan.madiun.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import net.winnerawan.madiun.ui.content_news.ContentNewsFragment;
import net.winnerawan.madiun.ui.helper.SlidingTabLayout;

public class NewsFragment extends BaseFragment implements NewsView {

    @Inject
    NewsMvpPresenter<NewsView> presenter;
//
//    @Inject
//    LinearLayoutManager newsLayoutManager;
//
//    @Inject
//    PostAdapter newsAdapter;
//
//    @Inject
//    HeadLineAdapter headLineAdapter;
//    @BindView(R.id.recycler_news)
//    RecyclerView mRecyclerView;
//
//    @BindView(R.id.recycler_headlines)
//    RecyclerView mRecyclerHeadLines;
    NewsTabPagerAdapter newsTabPagerAdapter;
    @BindView(R.id.tab_content_vp)
            
    ViewPager tabContentVp;
    @BindView(R.id.tab_news_layout)
    SlidingTabLayout tabNewsLayout;
    private int tabPosition = 0;
    private boolean isCategoryEnabled = false;

    public static NewsFragment newInstance() {

        Bundle args = new Bundle();

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

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
        //presenter.getNews();
        newsTabPagerAdapter = new NewsTabPagerAdapter(getBaseActivity(), getChildFragmentManager());
        tabContentVp.setAdapter(newsTabPagerAdapter);
        tabNewsLayout.setDistributeEvenly(false);
        tabNewsLayout.setSelectedIndicatorColors(R.color.colorAccent);
        tabNewsLayout.setCustomTabColorizer(position -> ContextCompat.getColor(getBaseActivity(), R.color.colorAccent));
        presenter.getCategories();
    }

    @Override
    public void addTab(Category category) {
        newsTabPagerAdapter.addFragment(ContentNewsFragment.newInstance(category), category.getName());
    }

    @Override
    public void setTabs(List<Category> categories) {
//        tabPagerAdapter.clearItems();

        /*
        //Ganti ke slidingtablayout
        tabNewsLayout.removeAllTabs();
        tabNewsLayout.removeAllViews();
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            tabNewsLayout.addTab(tabNewsLayout.newTab().setText(category.getName()));
            fragments.add(ContentNewsFragment.newInstance(category));
            titles.add(category.getName());
        }
        tabPagerAdapter.addFragments(fragments, titles);*/

    }

    @Override
    public void setEnabledMenuCategory(boolean b) {
        isCategoryEnabled = b;
        newsTabPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setOffScreenPageLimit(int size) {
        tabNewsLayout.setViewPager(tabContentVp);
        tabNewsLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                tabContentVp.setCurrentItem(position);
                tabPosition = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /*
        //FIXME ganti ke slidingtablayout
        tabContentVp.setOffscreenPageLimit(size);
        CommonUtils.changeTabsFont(getBaseActivity(), tabNewsLayout, R.color.white);
        tabContentVp.setCurrentItem(0);
        if (size > 0 ) {
            if(tabNewsLayout.getTabAt(0) != null) tabNewsLayout.getTabAt(0).select();
        }*/
    }

    @Override
    public void clearTabs() {
        newsTabPagerAdapter.clearItems();
        newsTabPagerAdapter.notifyDataSetChanged();
        tabContentVp.setOffscreenPageLimit(0);
        /*tabNewsLayout.removeAllTabs();
        tabPagerAdapter.clearItems();
        tabPagerAdapter.notifyDataSetChanged();
        tabContentVp.setOffscreenPageLimit(0);*/
    }


//    @Override
//    public void showHeadLines(List<Post> headlines) {
//        mRecyclerHeadLines.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.HORIZONTAL, false));
//        headLineAdapter.addItems(headlines);
//        mRecyclerHeadLines.setAdapter(headLineAdapter);
//    }
//
//    @Override
//    public void showNews(List<Post> news) {
//        mRecyclerView.setLayoutManager(newsLayoutManager);
//        newsAdapter.addItems(news);
//        mRecyclerView.setAdapter(newsAdapter);
//    }
}
