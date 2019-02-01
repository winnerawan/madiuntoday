package net.winnerawan.madiun.ui.tv;

import com.androidnetworking.error.ANError;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.AppLogger;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class TvPresenter<V extends TvView> extends BasePresenter<V> implements TvMvpPresenter<V> {

    @Inject
    public TvPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getVideos() {
        String key = getDataManager().getYoutubeKey();
        getCompositeDisposable().add(getDataManager().getVideos(key)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(videos -> {
                    if (videos == null) {
                        return;
                    }
                    getMvpView().showVideos(videos.getItems());
                    getMvpView().stopShimmer();
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

