package net.winnerawan.madiun.ui.tv;

import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class TvPresenter<V extends TvView> extends BasePresenter<V> implements TvMvpPresenter<V> {

    @Inject
    public TvPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

