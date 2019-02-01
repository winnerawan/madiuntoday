package net.winnerawan.madiun.ui.detail;

import com.androidnetworking.error.ANError;
import io.reactivex.functions.Consumer;
import net.winnerawan.madiun.data.network.model.Article;
import net.winnerawan.madiun.data.network.model.Category;
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
    }

    @Override
    public void getArticle(String url) {
        getCompositeDisposable().add(getDataManager().getArticle(url)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(article -> {
                    if (article!=null) {
                        getMvpView().showArticle(article);
                        if (article.getImages()!=null) {
                            getMvpView().showGalleries(article.getImages());
                        } else {
                           getMvpView().showImage();
                        }
                        getMvpView().stopShimmer();
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

    @Override
    public void getRelated(Category category) {
        getCompositeDisposable().add(getDataManager().getNews(category)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(posts -> {
                    if (!isViewAttached()) return;

                    if (posts == null) {
                        return;
                    }
                    getMvpView().showRelated(posts);
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

    @Override
    public String getIntersId() {
        return null;
    }
}
