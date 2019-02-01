package net.winnerawan.madiun.ui.news;

import com.androidnetworking.error.ANError;

import com.google.gson.Gson;
import net.winnerawan.madiun.data.DataManager;
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

        if (categories != null) {
            getMvpView().setTabs(categories);
            if (categories.get(0).getName().equalsIgnoreCase("KATEGORI")) {
                categories.remove(0);
            }
            AppLogger.e("kategori dr preference 1 asli: "+new Gson().toJson(categories));

            for (int i = 0; i < categories.size(); i++) {
//                categories.remove(0);
//                Category dbhcht = categories.get(i).getId().equals(105);
//                Category
//                if (categories.get(i).getId().equals(29) && categories.get(i).getId().equals(8) && ) {
//                    categories.re
//                }
                AppLogger.e("kategori dr preference 1: "+new Gson().toJson(categories));

                Category category = categories.get(i);
                if (category.getId().equals(AppConstants.CATEGORY_MADIUN_THIS_WEEK) || category.getParent().equals(AppConstants.CATEGORY_TV)) {
                    categories.remove(category);
                }
                if (category.getName().equalsIgnoreCase(AppConstants.NAME_MTDTV) || category.getSlug().equalsIgnoreCase(AppConstants.SLUG_MTDTV)) {
                    categories.remove(category);
                }
                if (category.getId().equals(AppConstants.CATEGORY_DBH_CHT)) {
                    categories.remove(category);
                }
                if (category.getId().equals(AppConstants.CATEGORY_FOTO_BERITA)) {
                    categories.remove(category);
                }
                if (category.getId().equals(AppConstants.CATEGORY_MADIUN_THIS_WEEK)) {
                    categories.remove(category);
                }
                if (category.getId().equals(AppConstants.CATEGORY_TV)) {
                    categories.remove(category);
                }


//                List<Category> finalCategories = categories.removeIf(category -> )
                getMvpView().addTab(categories.get(i));
            }
//            getMvpView().onError(categories.size());
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

                                    List<Category> currCategories = categoriesResponse;
                                    AppLogger.e("kategori asli : "+new Gson().toJson(currCategories));

                                    for (int i=0; i<currCategories.size(); i++) {
                                        Category category = currCategories.get(i);
                                        if (category.getId().equals(AppConstants.CATEGORY_MADIUN_THIS_WEEK) || category.getParent().equals(AppConstants.CATEGORY_TV)) {
                                            currCategories.remove(category);
                                        }
                                        if (category.getName().equalsIgnoreCase(AppConstants.NAME_MTDTV) || category.getSlug().equalsIgnoreCase(AppConstants.SLUG_MTDTV)) {
                                            currCategories.remove(category);
                                        }
                                        if (category.getId().equals(AppConstants.CATEGORY_DBH_CHT)) {
                                            currCategories.remove(category);
                                        }
                                        if (category.getId().equals(AppConstants.CATEGORY_FOTO_BERITA)) {
                                            currCategories.remove(category);
                                        }
                                        if (category.getId().equals(AppConstants.CATEGORY_MADIUN_THIS_WEEK)) {
                                            currCategories.remove(category);
                                        }
                                        if (category.getId().equals(AppConstants.CATEGORY_TV)) {
                                            currCategories.remove(category);
                                        }

                                    }
//                                    Category rekomendasi = new Category();
//                                    rekomendasi.setId("rekomendasi");
//                                    rekomendasi.setName("Rekomendasi");
//                                    currCategories.add(0, rekomendasi);
//                                    getDataManager().setCategories(new Categories(currCategories));
                                    getDataManager().setCategories(new Categories(currCategories));
                            /*getMvpView().showMessage("Proses Input : ");
                            Log.d("TES", "SABAR" : "+getDataManager().getCategoriesFromPref().size());
                            getMvpView().onError("Proses Input : "+new Gson().toJson(getDataManager().getCategoriesFromPref()));
//                            getMvpView().showMessage("Proses Input : "+new Gson().toJson(getDataManager().getCategoriesFromPref()));

                            if (getDataManager().getCategoriesFromPref() == null) {
                                getMvpView().showMessage("Null");
                                AppLogger.d("null");
                                Log.d("TES LAGI", "NULL");
                            } else if (getDataManager().getCategoriesFromPref().size() == 0) {
                                getMvpView().showMessage("Tidak ada data");
                                AppLogger.d("Tidak ada data");
                                Log.d("LAGI LAGI TES", "Tidak ada data");
                            } else {
                                getMvpView().showMessage("DATA : "+getDataManager().getCategoriesFromPref().size());
                                AppLogger.d("ONOK DATANE COK : "+getDataManager().getCategoriesFromPref().size());
                                Log.d("TES", "FFAAKKK!!! : "+getDataManager().getCategoriesFromPref().size());
                            }
                            Log.d("TES", "NULL");

                            getMvpView().setTabs(getDataManager().getCategoriesFromPref());
                            getMvpView().showMessage("FAK!!! ");
                            Log.d("TES", "NULL COK ");*/
//                            for (int i = 0; i < categoriesResponse.getCategories().size(); i++) {
                                    List<Category> categories2 = getDataManager().getCategoriesFromPref().getCategories();
                                    AppLogger.e("kategori dr preference 2: "+new Gson().toJson(categories2));

//                                    getMvpView().setTabs(categories2);
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
        if (categories != null) {
            categories.remove(0);
            if (categories.get(0).getName().equalsIgnoreCase("KATEGORI")) {
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
