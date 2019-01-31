package net.winnerawan.madiun.ui.news;

import net.winnerawan.madiun.ui.base.MvpPresenter;

public interface NewsMvpPresenter<V extends NewsView> extends MvpPresenter<V> {

    void getCategories();
}
