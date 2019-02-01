package net.winnerawan.madiun.ui.splash;

import javax.inject.Inject;

import com.androidnetworking.error.ANError;
import net.winnerawan.madiun.data.network.Ads;
import net.winnerawan.madiun.data.network.App;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.AppLogger;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.madiun.data.DataManager;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public class SplashPresenter<V extends SplashView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void app() {
        getCompositeDisposable().add(getDataManager().getAds()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(app -> {
                    if (app!=null) {
                        getDataManager().setYoutubeKey(app.getYoutubeKey());
                        getDataManager().setBanner(app.getAds().getBanner());
                        getDataManager().setInters(app.getAds().getInter());
                        getDataManager().setAdEnable(app.isAdsEnable());
                        //AppLogger.e("ADS: "+app.isAdsEnable());
                    }

                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }
}
