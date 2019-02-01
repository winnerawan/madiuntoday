package net.winnerawan.madiun.ui.player;

import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class PlayerPresenter<V extends PlayerView> extends BasePresenter<V> implements PlayerMvpPresenter<V> {

    @Inject
    public PlayerPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
