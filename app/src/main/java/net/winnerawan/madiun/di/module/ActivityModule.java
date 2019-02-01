package net.winnerawan.madiun.di.module;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import net.winnerawan.madiun.di.PerActivity;
import net.winnerawan.madiun.ui.about.AboutMvpPresenter;
import net.winnerawan.madiun.ui.about.AboutPresenter;
import net.winnerawan.madiun.ui.about.AboutView;
import net.winnerawan.madiun.ui.adapter.HeadLineAdapter;
import net.winnerawan.madiun.ui.adapter.NewsAdapter;
import net.winnerawan.madiun.ui.adapter.PostAdapter;
import net.winnerawan.madiun.ui.category.*;
import net.winnerawan.madiun.ui.content_news.ContentNewsMvpPresenter;
import net.winnerawan.madiun.ui.content_news.ContentNewsPresenter;
import net.winnerawan.madiun.ui.content_news.ContentNewsView;
import net.winnerawan.madiun.ui.dbhcht.DBHCHTMvpPresenter;
import net.winnerawan.madiun.ui.dbhcht.DBHCHTPresenter;
import net.winnerawan.madiun.ui.dbhcht.DBHCHTView;
import net.winnerawan.madiun.ui.detail.DetailMvpPresenter;
import net.winnerawan.madiun.ui.gallery.GalleryMvpPresenter;
import net.winnerawan.madiun.ui.gallery.GalleryPresenter;
import net.winnerawan.madiun.ui.gallery.GalleryView;
import net.winnerawan.madiun.ui.main.MainPresenter;
import net.winnerawan.madiun.ui.detail.DetailView;
import net.winnerawan.madiun.ui.main.MainView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import dagger.Module;
import dagger.Provides;

import net.winnerawan.madiun.di.ActivityContext;
import net.winnerawan.madiun.ui.news.NewsMvpPresenter;
import net.winnerawan.madiun.ui.news.NewsPresenter;
import net.winnerawan.madiun.ui.news.NewsView;
import net.winnerawan.madiun.ui.player.PlayerMvpPresenter;
import net.winnerawan.madiun.ui.player.PlayerPresenter;
import net.winnerawan.madiun.ui.player.PlayerView;
import net.winnerawan.madiun.ui.search.SearchMvpPresenter;
import net.winnerawan.madiun.ui.search.SearchPresenter;
import net.winnerawan.madiun.ui.search.SearchView;
import net.winnerawan.madiun.ui.splash.SplashMvpPresenter;
import net.winnerawan.madiun.ui.splash.SplashView;
import net.winnerawan.madiun.ui.tv.TvMvpPresenter;
import net.winnerawan.madiun.ui.tv.TvPresenter;
import net.winnerawan.madiun.ui.tv.TvView;
import net.winnerawan.madiun.ui.tv.VideosAdapter;
import net.winnerawan.madiun.ui.webview.WebviewMvpPresenter;
import net.winnerawan.madiun.ui.webview.WebviewPresenter;
import net.winnerawan.madiun.ui.webview.WebviewView;
import net.winnerawan.madiun.utils.rx.AppSchedulerProvider;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.madiun.ui.detail.DetailPresenter;
import net.winnerawan.madiun.ui.main.MainMvpPresenter;
import net.winnerawan.madiun.ui.splash.SplashPresenter;

import java.util.ArrayList;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashView> provideSplashPresenter(
            SplashPresenter<SplashView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainView> provideMainPresenter(
            MainPresenter<MainView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DetailMvpPresenter<DetailView> provideDetailPresenter(
            DetailPresenter<DetailView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    WebviewMvpPresenter<WebviewView> provideWebviewPresenter(
            WebviewPresenter<WebviewView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    CategoryMvpPresenter<CategoryView> provideCategoryPresenter(
            CategoryPresenter<CategoryView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SearchMvpPresenter<SearchView> provideSearchPresenter(
            SearchPresenter<SearchView> presenter) {
        return presenter;
    }

    @Provides
    AdRequest provideAdRequest() {
        return new AdRequest.Builder()
//                .addTestDevice("C85CBB66E44AAAF0BE0F3940290BE1BB") //lg
//                .addTestDevice("87FD9AD2CA0E7597091B3C08774DFCA0") //samsung
                .build();
    }

    @Provides
    AdView provideAdView() {
        return new AdView(mActivity.getApplicationContext());
    }

    @Provides
    NewsMvpPresenter<NewsView> provideNewsPresenter(
            NewsPresenter<NewsView> presenter) {
        return presenter;
    }

    @Provides
    TvMvpPresenter<TvView> provideTvPresenter(
            TvPresenter<TvView> presenter) {
        return presenter;
    }

    @Provides
    GalleryMvpPresenter<GalleryView> provideGalleryPresenter(
            GalleryPresenter<GalleryView> presenter) {
        return presenter;
    }

    @Provides
    DBHCHTMvpPresenter<DBHCHTView> provideDBHCHTPresenter(
            DBHCHTPresenter<DBHCHTView> presenter) {
        return presenter;
    }

    @Provides
    AboutMvpPresenter<AboutView> provideAboutPresenter(
            AboutPresenter<AboutView> presenter) {
        return presenter;
    }

    @Provides
    ContentNewsMvpPresenter<ContentNewsView> provideContentNewsPresenter(
            ContentNewsPresenter<ContentNewsView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    PlayerMvpPresenter<PlayerView> providePlayerPresenter(
            PlayerPresenter<PlayerView> presenter) {
        return presenter;
    }

    @Provides
    NewsAdapter provideNewsAdapter() {
        return new NewsAdapter(mActivity.getApplicationContext(), new ArrayList<>());
    }

    @Provides
    PostAdapter providePostAdapter() {
        return new PostAdapter(new ArrayList<>());
    }

    @Provides
    HeadLineAdapter provideHeadlinesAdapter() {
        return new HeadLineAdapter(new ArrayList<>());
    }

    @Provides
    VideosAdapter provideVideosAdapter() {
        return new VideosAdapter(new ArrayList<>());
    }

    @Provides
    CategoryOtherAdapter provideCategoryOtherAdapter() {
        return new CategoryOtherAdapter(new ArrayList<>());
    }

    @Provides
    CategoryAdapter provideCategoryAdapter() {
        return new CategoryAdapter(new ArrayList<>());
    }

//    @Provides
//    GalleryAdapter provideGalleryAdapter() {
//        return new GalleryAdapter(new ArrayList<>());
//    }

//    @Provides
//    GallerySliderAdapter provideGallerySliderAdapter() {
//        return new GallerySliderAdapter(new ArrayList<>());
//    }

}
