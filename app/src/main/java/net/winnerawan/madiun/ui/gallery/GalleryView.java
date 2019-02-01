package net.winnerawan.madiun.ui.gallery;

import net.winnerawan.madiun.data.network.model.Gallery;
import net.winnerawan.madiun.ui.base.MvpView;

import java.util.List;

public interface GalleryView extends MvpView {

    void stopShimmer();

    void showGalleries(List<Gallery> galleries);

}
