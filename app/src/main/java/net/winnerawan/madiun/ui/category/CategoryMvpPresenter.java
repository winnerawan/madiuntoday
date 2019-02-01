package net.winnerawan.madiun.ui.category;

import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.di.PerActivity;
import net.winnerawan.madiun.ui.base.MvpPresenter;

import java.util.List;

@PerActivity
public interface CategoryMvpPresenter<V extends CategoryView> extends MvpPresenter<V> {
    void loadCategories();

    void loadCategoriesOther();

    void removeItemCategory(Category category);

    void addItemCategory(Category category);

    void replaceCurrentCategories(List<Category> list);
}
