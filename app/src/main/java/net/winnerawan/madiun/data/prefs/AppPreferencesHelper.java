package net.winnerawan.madiun.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import net.winnerawan.madiun.data.network.model.Categories;
import net.winnerawan.madiun.di.ApplicationContext;
import net.winnerawan.madiun.di.PreferenceInfo;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String KEY_LOGGED_IN = "KEY_LOGGED_IN";
    private static final String KEY_CLIENT_ID = "KEY_CLIENT_ID";
    private static final String KEY_CLIENT_SECRET = "KEY_CLIENT_SECRET";
    private static final String KEY_CONTENT_TYPE = "KEY_CONTENT_TYPE";
    private static final String KEY_FIRST_TIME = "KEY_FIRST_TIME";
    private static final String KEY_CATEGORY = "KEY_CATEGORY";

    private static final String KEY_REWARD = "KEY_REWARD";
    private static final String KEY_BANNER = "KEY_BANNER";
    private static final String KEY_INTERS = "KEY_INTERS";


    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isLoggedIn() {
        return mPrefs.getBoolean(KEY_LOGGED_IN, false);
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        mPrefs.edit().putBoolean(KEY_LOGGED_IN, loggedIn).apply();
    }

    @Override
    public boolean isFirstTime() {
        return mPrefs.getBoolean(KEY_FIRST_TIME, true);
    }

    @Override
    public void setFirstTime(boolean isFirstTime) {
        mPrefs.edit().putBoolean(KEY_FIRST_TIME, isFirstTime).apply();
    }

    @Override
    public void setBanner(String banner) {
        mPrefs.edit().putString(KEY_BANNER, banner).apply();
    }

    @Override
    public void setInters(String inters) {
        mPrefs.edit().putString(KEY_INTERS, inters).apply();

    }

    @Override
    public void setReward(String reward) {
        mPrefs.edit().putString(KEY_REWARD, reward).apply();

    }

    @Override
    public String getBanner() {
        return mPrefs.getString(KEY_BANNER, "ca-app-pub-9400864410179150/8823258458");
    }

    @Override
    public String getInters() {
        return mPrefs.getString(KEY_INTERS, "ca-app-pub-9400864410179150/8631686768");
    }

    @Override
    public String getReward() {
        return mPrefs.getString(KEY_REWARD, "ca-app-pub-9400864410179150/1281995578");
    }

    @Override
    public void setCategories(Categories categories) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        String json = gson.toJson(categories);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(KEY_CATEGORY, json);
        editor.apply();
    }

    @Override
    public void clearCategoriesFromPref() {
        mPrefs.edit().putString(KEY_CATEGORY, "").apply();
    }

    @Override
    public Categories getCategoriesFromPref() {
        String json = mPrefs.getString(KEY_CATEGORY, "");
        Categories categories = new Categories();
        if (!json.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonObject data = (JsonObject) parser.parse(json);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
            categories = gson.fromJson(data, new TypeToken<Categories>() {
            }.getType());
        }
        return categories;
    }
}
