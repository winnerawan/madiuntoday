package net.winnerawan.madiun.ui.tv;

import net.winnerawan.madiun.ui.base.MvpPresenter;

public interface TvMvpPresenter<V extends TvView> extends MvpPresenter<V> {

    void getVideos();
}
