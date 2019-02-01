package net.winnerawan.madiun.ui.search;


import net.winnerawan.madiun.ui.base.MvpPresenter;

public interface SearchMvpPresenter<V extends SearchView> extends MvpPresenter<V> {

    void search(String keyword);
}
