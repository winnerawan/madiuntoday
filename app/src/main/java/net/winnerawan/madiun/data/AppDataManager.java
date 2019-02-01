package net.winnerawan.madiun.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.winnerawan.madiun.data.db.DbHelper;
import net.winnerawan.madiun.data.network.Ads;
import net.winnerawan.madiun.data.network.ApiHelper;
import net.winnerawan.madiun.data.network.App;
import net.winnerawan.madiun.data.network.model.*;
import net.winnerawan.madiun.data.network.response.GalleryResponse;
import net.winnerawan.madiun.data.network.response.YoutubeResponse;
import net.winnerawan.madiun.data.prefs.PreferencesHelper;
import net.winnerawan.madiun.di.ApplicationContext;

import java.util.List;

import io.reactivex.Single;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

//    @Override
//    public ApiHeader getApiHeader() {
//        return mApiHelper.getApiHeader();
//    }


    @Override
    public boolean isLoggedIn() {
        return mPreferencesHelper.isLoggedIn();
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        mPreferencesHelper.setLoggedIn(loggedIn);
    }


    @Override
    public boolean isFirstTime() {
        return mPreferencesHelper.isFirstTime();
    }

    @Override
    public void setFirstTime(boolean isFirstTime) {
        mPreferencesHelper.setFirstTime(isFirstTime);
    }

    @Override
    public void setBanner(String banner) {
        mPreferencesHelper.setBanner(banner);
    }

    @Override
    public void setInters(String inters) {
        mPreferencesHelper.setInters(inters);
    }

    @Override
    public void setReward(String reward) {
        mPreferencesHelper.setReward(reward);
    }

    @Override
    public String getBanner() {
        return mPreferencesHelper.getBanner();
    }

    @Override
    public String getInters() {
        return mPreferencesHelper.getInters();
    }

    @Override
    public String getReward() {
        return mPreferencesHelper.getReward();
    }

    @Override
    public Single<App> getAds() {
        return mApiHelper.getAds();
    }

    @Override
    public Single<List<Category>> getCategories() {
        return mApiHelper.getCategories();
    }

    @Override
    public boolean isAdEnable() {
        return mPreferencesHelper.isAdEnable();
    }

    @Override
    public void setAdEnable(boolean isEnable) {
        mPreferencesHelper.isAdEnable();
    }

    @Override
    public void setYoutubeKey(String youtubeKey) {
        mPreferencesHelper.setYoutubeKey(youtubeKey);
    }

    @Override
    public String getYoutubeKey() {
        return mPreferencesHelper.getYoutubeKey();
    }

    @Override
    public Single<List<Post>> getPosts() {
        return mApiHelper.getPosts();
    }

    @Override
    public void setCategories(Categories categories) {
        mPreferencesHelper.setCategories(categories);
    }

    @Override
    public void clearCategoriesFromPref() {
        mPreferencesHelper.clearCategoriesFromPref();
    }

    @Override
    public Categories getCategoriesFromPref() {
        return mPreferencesHelper.getCategoriesFromPref();
    }

    @Override
    public void clearCategoriesOtherFromPref() {
        mPreferencesHelper.clearCategoriesOtherFromPref();
    }

    @Override
    public void setCategoriesOther(Categories categories) {
        mPreferencesHelper.setCategoriesOther(categories);
    }

    @Override
    public Categories getCategoriesOtherFromPref() {
        return mPreferencesHelper.getCategoriesOtherFromPref();
    }

    @Override
    public Single<List<Post>> getNews(Category category) {
        return mApiHelper.getNews(category);
    }

    @Override
    public Single<GalleryResponse> fetchGallery(String url) {
        return mApiHelper.fetchGallery(url);
    }

    @Override
    public Single<List<Gallery>> getGalleries(Category category) {
        return mApiHelper.getGalleries(category);
    }

    @Override
    public Single<Article> getArticle(String url) {
        return mApiHelper.getArticle(url);
    }

    @Override
    public Single<List<Post>> search(String keyword) {
        return mApiHelper.search(keyword);
    }

    @Override
    public Single<YoutubeResponse> getVideos(String key) {
        return mApiHelper.getVideos(key);
    }
}

