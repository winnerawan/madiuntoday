package net.winnerawan.madiun.ui.stream;

import com.androidnetworking.error.ANError;
import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.AppLogger;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class StreamPresenter<V extends StreamView> extends BasePresenter<V> implements StreamMvpPresenter<V> {

    @Inject
    public StreamPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }



}
