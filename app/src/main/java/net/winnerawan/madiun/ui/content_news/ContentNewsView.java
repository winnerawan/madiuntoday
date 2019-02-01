package net.winnerawan.madiun.ui.content_news;

import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.base.MvpView;

import java.util.List;

public interface ContentNewsView extends MvpView {
//    void displayArticles(List<Article> articles);

    void setEnableRefreshLayout();
//
    void stopShimmer();
    void setDisableRefreshLayout();
    void showNews(List<Post> news);
}
