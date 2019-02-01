package net.winnerawan.madiun.ui.content_news;


import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.ui.base.MvpPresenter;

/**
 * Created by adriyo on 11/15/17.
 * adriyo.github.io
 */

public interface ContentNewsMvpPresenter<V extends ContentNewsView> extends MvpPresenter<V> {
//    void loadNews(Category category);
    void getNews(Category category, int page);

}
