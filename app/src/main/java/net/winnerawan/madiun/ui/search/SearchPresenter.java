package net.winnerawan.madiun.ui.search;

import com.androidnetworking.error.ANError;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

public class SearchPresenter<V extends SearchView> extends BasePresenter<V> implements SearchMvpPresenter<V> {

    @Inject
    public SearchPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void search(String keyword) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager().search(keyword)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(response -> {
                    if (!isViewAttached()) return;

                    if (response == null) {
                        return;
                    }
                    getMvpView().showNews(response);
                    getMvpView().hideLoading();
                    getMvpView().hideKeyboard();
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    // handle the error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }

}
