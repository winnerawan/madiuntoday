package net.winnerawan.madiun.ui.dbhcht;

import com.androidnetworking.error.ANError;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DBHCHTPresenter<V extends DBHCHTView> extends BasePresenter<V> implements DBHCHTMvpPresenter<V> {

    @Inject
    public DBHCHTPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public String getInters() {
        return getDataManager().getInters();
    }

    @Override
    public void getDbhCht(Category category, int page) {
        getCompositeDisposable().add(getDataManager().getNews(category, page)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(posts -> {
                    if (!isViewAttached()) return;
                    getMvpView().setDisableRefreshLayout();

                    if (posts == null) {
                        return;
                    }
                    getMvpView().showContents(posts);
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
