package net.winnerawan.madiun.ui.main;

import net.winnerawan.madiun.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends MainView> extends MvpPresenter<V> {

    String getIntersId();

    boolean isAdsEnable();
}
