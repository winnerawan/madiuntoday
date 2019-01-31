package net.winnerawan.madiun.ui.webview;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

public class WebviewPresenter<V extends WebviewView> extends BasePresenter<V>
        implements WebviewMvpPresenter<V> {

    @Inject
    WebviewPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
