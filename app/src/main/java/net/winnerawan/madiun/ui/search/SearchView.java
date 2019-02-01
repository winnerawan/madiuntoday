package net.winnerawan.madiun.ui.search;

import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.base.MvpView;

import java.util.List;


public interface SearchView extends MvpView {
    void showNews(List<Post> news);
}
