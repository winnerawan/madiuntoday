package net.winnerawan.madiun.ui.detail;

import net.winnerawan.madiun.utils.rx.SchedulerProvider;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class DetailPresenter<V extends DetailView> extends BasePresenter<V> implements DetailMvpPresenter<V> {

    @Inject
    public DetailPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        getMvpView().showPost();
    }

    @Override
    public String getIntersId() {
        return null;
    }
}
