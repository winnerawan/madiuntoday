package net.winnerawan.madiun.ui.category;

import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.ui.base.MvpView;

import java.util.List;


public interface CategoryView extends MvpView {
    void displayCategories(List<Category> categories);

    void setMenuEditEnabled();

    void categoriesAdapterNotifyChanged();

    void postTabEvent();

    void displayCategoriesOther(List<Category> categories);

    String getCategoryTitleName();
}
