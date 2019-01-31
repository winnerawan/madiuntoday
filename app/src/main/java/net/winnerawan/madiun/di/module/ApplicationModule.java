package net.winnerawan.madiun.di.module;

import android.app.Application;
import android.content.Context;



import javax.inject.Singleton;

import net.winnerawan.madiun.data.db.DbHelper;
import net.winnerawan.madiun.utils.AppConstants;
import dagger.Module;
import dagger.Provides;
import net.winnerawan.madiun.data.AppDataManager;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.data.db.AppDbHelper;
import net.winnerawan.madiun.data.network.ApiHelper;
import net.winnerawan.madiun.data.network.AppApiHelper;
import net.winnerawan.madiun.data.prefs.AppPreferencesHelper;
import net.winnerawan.madiun.data.prefs.PreferencesHelper;
import net.winnerawan.madiun.di.ApplicationContext;
import net.winnerawan.madiun.di.DatabaseInfo;
import net.winnerawan.madiun.di.PreferenceInfo;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

//    @Provides
//    @Singleton
//    ApiHeader provideApiHeader(DbHelper header) {
//        return header;
//    }



}
