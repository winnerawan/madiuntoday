package net.winnerawan.madiun.ui.news;

import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.base.MvpView;

import java.util.List;

public interface NewsView extends MvpView {

//    void showHeadLines(List<Post> headlines);
//    void showNews(List<Post> news);

    void addTab(Category category);

    void setOffScreenPageLimit(int size);

    void setEnabledMenuCategory(boolean b);

    void setTabs(List<Category> categories);

    void clearTabs();

    String getCategoryTitleName();
}
