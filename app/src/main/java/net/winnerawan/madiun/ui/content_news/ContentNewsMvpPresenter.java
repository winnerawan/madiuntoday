package net.winnerawan.madiun.ui.content_news;


import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.ui.base.MvpPresenter;

public interface ContentNewsMvpPresenter<V extends ContentNewsView> extends MvpPresenter<V> {
    void getNews(Category category, int page);
}
