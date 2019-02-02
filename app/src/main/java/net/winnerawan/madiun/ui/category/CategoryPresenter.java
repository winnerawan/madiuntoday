package net.winnerawan.madiun.ui.category;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;
import io.reactivex.disposables.CompositeDisposable;
import net.winnerawan.madiun.data.DataManager;
import net.winnerawan.madiun.data.network.model.Categories;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.ui.base.BasePresenter;
import net.winnerawan.madiun.utils.AppLogger;
import net.winnerawan.madiun.utils.rx.SchedulerProvider;

public class CategoryPresenter<V extends CategoryView>
        extends BasePresenter<V>
        implements CategoryMvpPresenter<V> {

    @Inject
    CategoryPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadCategories() {
        List<Category> categories = getDataManager().getCategoriesFromPref().getCategories();
        int size = categories.size();

        if (size > 0) {
            if (!categories.get(0).getName().equalsIgnoreCase(getMvpView().getCategoryTitleName())) {
                Category category = new Category();
                category.setName(getMvpView().getCategoryTitleName());
                categories.add(0, category);//fixme
                Log.d("TES_CHANNELQ", "channelq gak sama");
            }
            getMvpView().displayCategories(categories);
            getMvpView().setMenuEditEnabled();
            return;
        }

        getMvpView().displayCategories(getDataManager().getCategoriesFromPref().getCategories());
        /*getCompositeDisposable().add(getDataManager()
                .getCategories()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(
                        categoriesResponse -> {
                            if (!isViewAttached()) return;

                            if (categoriesResponse == null) {
                                getMvpView().onError(R.string.api_default_error);
                                return;
                            }

                            if (!categoriesResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                                getMvpView().onError(R.string.api_default_error);
                                return;
                            }

                            if (categoriesResponse.getCategories() == null) {
                                getMvpView().onError(R.string.api_default_error);
                                return;
                            }

                            List<Category> categories = categoriesResponse.getCategories();
                            int size = categories.size();
                            if (size > 0) {
                                categories.add(0, new Category("Channelku"));
                                getMvpView().displayCategories(categories);
                                getMvpView().setMenuEditEnabled();
                                return;
                            }

                            getMvpView().displayCategories(categoriesResponse.getCategories());

                        }, throwable -> {
                            if (!isViewAttached()) return;

                            if (throwable instanceof ANError) {
                                ANError anError = (ANError) throwable;
                                handleApiError(anError);
                            }
                        }
                )
        );*/
    }

    @Override
    public void replaceCurrentCategories(List<Category> list) {
        if (list.size() > 0) {
            list.remove(0);
        }
        getDataManager().clearCategoriesFromPref();
        if (list.size() > 0 && !list.get(0).getName().equalsIgnoreCase(getMvpView().getCategoryTitleName())) {
            Category category = new Category();
            category.setName(getMvpView().getCategoryTitleName());
            list.add(0, category); //fixme
        }
        getDataManager().setCategories(new Categories(list));
        getMvpView().categoriesAdapterNotifyChanged();
    }

    @Override
    public void loadCategoriesOther() {
        List<Category> categories = getDataManager().getCategoriesOtherFromPref().getCategories();
        if (categories == null) {
            categories = new ArrayList<>();
            getMvpView().displayCategoriesOther(categories);
            return;
        }
        int size = categories.size();
        if (size > 0) {
            if (!categories.get(0).getName().equalsIgnoreCase("KATEGORI LAIN")) {
                Category category = new Category();
                category.setName("LAINNYA");
                categories.add(0, category); //fixme
                Log.d("TES_CHANNEL", "channel gak sama");
            }
            getMvpView().displayCategoriesOther(categories);
            getMvpView().setMenuEditEnabled();
            return;
        }

        getMvpView().displayCategoriesOther(getDataManager().getCategoriesOtherFromPref().getCategories());
    }

    @Override
    public void removeItemCategory(Category category) {
        List<Category> categories = getDataManager().getCategoriesFromPref().getCategories();
        List<Category> categoriesOther = getDataManager().getCategoriesOtherFromPref().getCategories();
        getDataManager().clearCategoriesFromPref();
        getDataManager().clearCategoriesOtherFromPref();
        if (categories == null) {
            categories = new ArrayList<>();
        }
        if (categoriesOther == null) {
            categoriesOther = new ArrayList<>();
        }
        for (Category item : categories) {
            if (category.equals(item)) {
                categories.remove(category);
                break;
            } else {
                Log.d("TES", "gak sama");
            }
        }
        categoriesOther.add(category);
        Log.d("size", "after : " + categories.size());
        getDataManager().setCategories(new Categories(categories));
        getDataManager().setCategoriesOther(new Categories(categoriesOther));
        getMvpView().categoriesAdapterNotifyChanged();
        getMvpView().postTabEvent();
    }

    @Override
    public void addItemCategory(Category category) {
        List<Category> categories = getDataManager().getCategoriesFromPref().getCategories();
        List<Category> categoriesOther = getDataManager().getCategoriesOtherFromPref().getCategories();
        getDataManager().clearCategoriesFromPref();
        getDataManager().clearCategoriesOtherFromPref();
        if (categories == null) {
            categories = new ArrayList<>();
        }
        if (categoriesOther == null) {
            categoriesOther = new ArrayList<>();
        }
        categories.add(category);
        categoriesOther.remove(category);
        getDataManager().setCategories(new Categories(categories));
        getDataManager().setCategoriesOther(new Categories(categoriesOther));
        getMvpView().categoriesAdapterNotifyChanged();
        getMvpView().postTabEvent();
    }
}
