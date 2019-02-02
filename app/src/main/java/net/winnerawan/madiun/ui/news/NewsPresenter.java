package net.winnerawan.madiun.ui.news;

import com.androidnetworking.error.ANError;

import com.google.gson.Gson;
import net.winnerawan.madiun.R;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.data.network.App;
import net.winnerawan.madiun.data.network.model.Categories;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.AppConstants;
import net.winnerawan.madiun.utils.AppLogger;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class NewsPresenter<V extends NewsView> extends BasePresenter<V> implements NewsMvpPresenter<V> {

    @Inject
    public NewsPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getCategories() {

        List<Category> categories = getDataManager().getCategoriesFromPref().getCategories();

        if (categories != null && categories.size() > 0 && categories.get(0) != null && !categories.get(0).getName().isEmpty()) {
            if (categories.get(0).getName().equalsIgnoreCase(getMvpView().getCategoryTitleName())) {
                categories.remove(0);
            }
            for (int i = 0; i < categories.size(); i++) {
                getMvpView().addTab(categories.get(i));
            }
            getMvpView().setEnabledMenuCategory(true);
            getMvpView().setOffScreenPageLimit(categories.size());
            return;
        }

        getCompositeDisposable().add(getDataManager()
                .getCategories()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(
                        categoriesResponse -> {
                            checkViewAttached();
                            if (categoriesResponse == null) {
                                getMvpView().onError(R.string.api_default_error);
                                return;
                            }

                            if (categoriesResponse.size() == 0) {
                                getMvpView().onError(R.string.api_default_error);
                                return;
                            }
                            List<Category> currCategories = categoriesResponse;
                            getDataManager().setCategories(new Categories(categoriesResponse));

                            List<Category> categories2 = getDataManager().getCategoriesFromPref().getCategories();

                            for (int i = 0; i < categories2.size(); i++) {
                                getMvpView().addTab(categories2.get(i));
                            }

                            if (categories2.size() > 0) {
                                getMvpView().setEnabledMenuCategory(true);
                                getMvpView().setOffScreenPageLimit(categories2.size());
                            }


                        }, throwable -> {
                            checkViewAttached();
                            if (throwable instanceof ANError) {
                                ANError anError = (ANError) throwable;
                                handleApiError(anError);
                            }
                        }
                )
        );
    }

    @Override
    public void refreshTab() {
        getMvpView().showLoading();
        getMvpView().clearTabs();
        List<Category> categories = getDataManager().getCategoriesFromPref().getCategories();
        if (categories != null && categories.size() > 0 && categories.get(0) != null && !categories.get(0).getName().isEmpty()) {
            if (categories.get(0).getName().equalsIgnoreCase(getMvpView().getCategoryTitleName())) {
                categories.remove(0);
            }
            for (int i = 0; i < categories.size(); i++) {
                getMvpView().addTab(categories.get(i));
            }
            getMvpView().setEnabledMenuCategory(true);
            getMvpView().setOffScreenPageLimit(categories.size());
        }
        getMvpView().hideLoading();
    }
}
