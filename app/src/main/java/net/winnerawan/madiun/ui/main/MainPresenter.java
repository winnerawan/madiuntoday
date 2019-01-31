package net.winnerawan.madiun.ui.main;

import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;
import net.winnerawan.madiun.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class MainPresenter<V extends MainView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    @Inject
    public MainPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public String getIntersId() {
        return getDataManager().getInters();
    }
}
