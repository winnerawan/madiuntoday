package net.winnerawan.madiun.ui.detail;

import net.winnerawan.madiun.data.network.model.Article;
import net.winnerawan.madiun.data.network.model.Image;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.base.MvpView;

import java.util.List;

public interface DetailView extends MvpView {

    void showArticle(Article article);

    void showGalleries(List<Image> images);

    void showRelated(List<Post> posts);

    void stopShimmer();
    void showImage();
}
