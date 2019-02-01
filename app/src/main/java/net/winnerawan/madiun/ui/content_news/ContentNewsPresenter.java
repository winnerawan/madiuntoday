package net.winnerawan.madiun.ui.content_news;

import com.androidnetworking.error.ANError;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

/**
 * Created by adriyo on 11/15/17.
 * adriyo.github.io
 */

public class ContentNewsPresenter<V extends ContentNewsView> extends BasePresenter<V>
        implements ContentNewsMvpPresenter<V> {

    @Inject
    ContentNewsPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getNews(Category category, int page) {
        getCompositeDisposable().add(getDataManager().getNews(category, page)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(posts -> {
                    if (!isViewAttached()) return;
                    getMvpView().setDisableRefreshLayout();

                    if (posts == null) {
                        return;
                    }
                    getMvpView().showNews(posts);
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
//    @Override
//    public void loadNews(Category category) {
//        getMvpView().setEnableRefreshLayout();
//        getCompositeDisposable().add(getDataManager()
//                        .getArticleByCategory(category.getId())
//                        .subscribeOn(getSchedulerProvider().io())
//                        .observeOn(getSchedulerProvider().ui())
//                        .subscribe(
//                                articleResponse -> {
//                                    if (!isViewAttached()) return;
//                                    getMvpView().setDisableRefreshLayout();
//
//                                    if (articleResponse == null) {
//                                        return;
//                                    }
//
//                                    getMvpView().displayArticles(articleResponse.getArticles());
//
//                                }, throwable -> {
//                                    if (!isViewAttached()) return;
//                                    getMvpView().setDisableRefreshLayout();
//
//                                    if (throwable instanceof ANError) {
////                                ANError anError = (ANError) throwable;
////                                handleApiError(anError);
//                                        getMvpView().displayArticles(new ArrayList<>());
//                                    }
//                                }
//                        )
//        );
////        getMvpView().displayNews(getDummyNews());
//    }

//    List<Article> getDummyNews() {
//        List<Article> list = new ArrayList<>();
//        list.add(new Article(
//                "Ketua RT Jadi Tersangka Kasus Mengarak Pasangan yang Dituduh Mesum",
//                "14/11/2017, 18:59 WIB",
//                "http://assets.kompas.com/crop/0x80:1000x746/750x500/data/photo/2017/11/14/26891906123.jpg"));
//        list.add(new Article(
//                "Musim Hujan, Polisi Awasi Ciledug Indah, Periuk, dan Total Persada",
//                "14/11/2017, 23:56 WIB",
//                "http://assets.kompas.com/crop/0x0:780x390/780x390/data/photo/2016/03/01/0834370rps20160301-083315780x390.jpg"));
//        list.add(new Article(
//                "Ayo Ngobrol Bareng Google, Facebook, Twitter, sampai Menkominfo di Festival Media AJI",
//                "14/11/2017, 18:59 WIB",
//                "http://assets.kompas.com/crop/138x0:836x465/177x117/data/photo/2017/11/14/2529658349.JPG"));
//        list.add(new Article(
//                "Kudeta Militer Gulingkan Mugabe Sedang Berlangsung?",
//                "14/11/2017, 18:59 WIB",
//                "http://assets.kompas.com/crop/22x0:694x448/177x117/data/photo/2017/11/14/2386583779.jpeg"));
//        return list;
//    }
}
