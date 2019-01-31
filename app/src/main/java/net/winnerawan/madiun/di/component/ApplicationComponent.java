package net.winnerawan.madiun.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import net.winnerawan.madiun.MADIUN;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.di.module.ApplicationModule;
import net.winnerawan.madiun.service.SyncService;
import dagger.Component;

import net.winnerawan.madiun.di.ApplicationContext;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MADIUN app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();}
