package net.winnerawan.madiun.ui.detail;

import net.winnerawan.madiun.ui.base.MvpPresenter;

public interface DetailMvpPresenter<V extends DetailView> extends MvpPresenter<V> {


    String getIntersId();
}
