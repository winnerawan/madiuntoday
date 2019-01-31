package net.winnerawan.madiun;

import android.app.Application;

import android.support.multidex.MultiDex;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import com.crashlytics.android.Crashlytics;
import net.winnerawan.madiun.utils.AppLogger;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.di.component.ApplicationComponent;
import net.winnerawan.madiun.di.component.DaggerApplicationComponent;
import net.winnerawan.madiun.di.module.ApplicationModule;
import com.google.android.gms.ads.MobileAds;
import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


import javax.inject.Inject;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public class MADIUN extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        MultiDex.install(this);
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/grobold.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build());
        mApplicationComponent.inject(this);
        AppLogger.init();
        MobileAds.initialize(this, getString(R.string.appid));
        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        System.gc();
    }

}
