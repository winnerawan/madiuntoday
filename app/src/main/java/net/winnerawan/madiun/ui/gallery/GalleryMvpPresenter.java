package net.winnerawan.madiun.ui.gallery;

import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.ui.base.MvpPresenter;

public interface GalleryMvpPresenter<V extends GalleryView> extends MvpPresenter<V> {

    void getGalleries(Category category, int page);
}
